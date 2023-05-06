package dev.epicsquid.superiorshields.shield

import dev.epicsquid.superiorshields.capability.SuperiorShieldCap
import dev.epicsquid.superiorshields.capability.absorbDamage
import dev.epicsquid.superiorshields.config.SuperiorShieldConfigItem
import dev.epicsquid.superiorshields.network.NetworkHandler
import dev.epicsquid.superiorshields.network.SuperiorShieldUpdatePacket
import dev.epicsquid.superiorshields.registry.AttributeRegistry
import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
import dev.epicsquid.superiorshields.shield.effects.*
import dev.epicsquid.superiorshields.shield.effects.EffectTrigger.*
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraftforge.network.PacketDistributor

abstract class AbstractSuperiorShield(
	val name: String,
	effectHandler: EffectHandler
) : SuperiorShield, EffectHandler by effectHandler {

	protected val config: SuperiorShieldConfigItem
		get() = TODO()

	override val capacity: Double
		get() = config.capacity.get()

	override val rate: Int
		get() = config.rate.get()

	override val delay: Int
		get() = config.delay.get()

	override fun applyShield(entity: LivingEntity, stack: ItemStack, damage: Float, source: DamageSource): Float {
		val shield = entity.shield
		if (shield.hp > 0) {
			applyEffect(Damage(entity, source, damage, stack))
			if (damage >= shield.hp) {
				applyEffect(Empty(entity, stack))
			}
		}
		val damageLeft = shield.absorbDamage(damage.toInt()).toFloat()

		// Check if we should update a client player
		if (entity is ServerPlayer) {
			updateClient(entity, shield)
		}

		return damageLeft
	}

	override fun shieldTick(entity: LivingEntity, shield: SuperiorShieldCap, stack: ItemStack) {
		// Make sure we are on the server
		if (entity.level.isClientSide) return

		// Get the shield stats
		val delayAttribute: Int = entity.getAttributeValue(AttributeRegistry.shieldDelay).toInt()
		val capacityAttribute: Int = entity.getAttributeValue(AttributeRegistry.shieldCapacity).toInt()
		val rateAttribute: Int = entity.getAttributeValue(AttributeRegistry.shieldRate).toInt()

		// Check if the shield is full
		if (shield.hp >= capacityAttribute) {
			if (shield.ticksFull % 20 == 0) {
				applyEffect(Full(entity, stack))

				// Full trigger has occurred, reset
				shield.ticksFull = 0
			}
			// Increment how long the shield has been full for
			shield.ticksFull++
		} else {
			// It's not, so start recharging cycle
			if (shield.ticksWithoutDamage >= delayAttribute) {
				if (shield.ticksSinceRecharge % rateAttribute == 0) {
					rechargeShield(stack, entity)
					if (entity is ServerPlayer) {
						updateClient(entity, shield)
					}
					applyEffect(Recharge(entity, stack))
					if (shield.hp >= capacityAttribute) {
						applyEffect(Filled(entity, stack))
					}
					// A recharge has occurred, reset
					shield.ticksSinceRecharge = 0
				}
				// Increment how long it has been since a recharge was attempted on the shield
				shield.ticksSinceRecharge++
			} else {
				// Increment how long it has been since the shield has been damaged
				shield.ticksWithoutDamage++
			}
		}

		// Repair the shield automatically if it is a Botania shield
		if (entity is Player && shield is BotaniaSuperiorShield) shield.repairWithMana(stack, entity)
	}

	private fun updateClient(player: ServerPlayer, shield: SuperiorShieldCap) {
		NetworkHandler.CHANNEL.send(
			PacketDistributor.PLAYER.with { player },
			SuperiorShieldUpdatePacket(shield.hp)
		)
	}

	private fun applyEffect(effectTrigger: EffectTrigger) {
		

		when (effectTrigger) {
			is Damage -> damageEffects.forEach { it(effectTrigger) }
			is Empty -> emptyEffects.forEach { it(effectTrigger) }
			is Filled -> filledEffects.forEach { it(effectTrigger) }
			is Full -> fullEffects.forEach { it(effectTrigger) }
			is Recharge -> rechargeEffects.forEach { it(effectTrigger) }
		}
	}
}