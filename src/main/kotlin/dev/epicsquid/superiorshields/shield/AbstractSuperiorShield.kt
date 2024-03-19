package dev.epicsquid.superiorshields.shield

import dev.epicsquid.superiorshields.capability.SuperiorShieldCap
import dev.epicsquid.superiorshields.capability.absorbDamage
import dev.epicsquid.superiorshields.capability.reset
import dev.epicsquid.superiorshields.compat.ArsCompat
import dev.epicsquid.superiorshields.config.SuperiorShieldStats
import dev.epicsquid.superiorshields.effects.DefaultEffectHandler
import dev.epicsquid.superiorshields.effects.EffectHandler
import dev.epicsquid.superiorshields.effects.EffectTrigger
import dev.epicsquid.superiorshields.effects.EffectTrigger.*
import dev.epicsquid.superiorshields.enchantment.AttributeProvider
import dev.epicsquid.superiorshields.enchantment.SuperiorShieldEnchantment
import dev.epicsquid.superiorshields.network.NetworkHandler
import dev.epicsquid.superiorshields.network.SuperiorShieldUpdatePacket
import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraftforge.network.PacketDistributor
import kotlin.math.roundToInt

abstract class AbstractSuperiorShield(
	val config: SuperiorShieldStats,
	private val effectHandler: EffectHandler = DefaultEffectHandler.NONE
) : SuperiorShield, EffectHandler {

	override val capacity: Int
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
		if (entity.level().isClientSide) return

		// Get the shield stats
		val delayAttribute: Int = shield.rechargeDelay
		val capacityAttribute: Int = shield.capacity
		val rateAttribute: Int = shield.rechargeRate

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
				// This is compat for 1.19.2 update to 3.0.0, so we don't need to keep this on the 1.20 port
				if (rateAttribute == 0) {
					onEquipShield(entity, shield, stack)
					return
				}

				// This is good code we have to keep
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
	}

	private fun updateClient(player: ServerPlayer, shield: SuperiorShieldCap) {
		NetworkHandler.CHANNEL.send(
			PacketDistributor.PLAYER.with { player },
			SuperiorShieldUpdatePacket(shield.hp, shield.capacity)
		)
	}

	override fun applyEffect(effectTrigger: EffectTrigger, scale: Int) {
		effectHandler.applyEffect(effectTrigger)
		applyEnchantmentEffect(effectTrigger, scale)

		ArsCompat.triggerReactive(effectTrigger)
	}

	private fun applyEnchantmentEffect(effectTrigger: EffectTrigger, scale: Int) {
		EnchantmentHelper.getEnchantments(effectTrigger.shieldStack).forEach { (enchantment, level) ->
			if (enchantment is SuperiorShieldEnchantment) {
				enchantment.applyEffect(effectTrigger, level + scale)
			}
		}
	}

	override fun onEquipShield(entity: LivingEntity, shield: SuperiorShieldCap, stack: ItemStack) {
		val (capacityAttribute, rateAttribute, delayAttribute) = calculateShieldAttributes(stack)
		shield.capacity = capacityAttribute
		shield.rechargeRate = rateAttribute
		shield.rechargeDelay = delayAttribute
		shield.ticksWithoutDamage = 0
		shield.ticksSinceRecharge = 0
		shield.ticksFull = 0
		shield.hp = shield.hp.coerceAtMost(shield.capacity)

		if (entity is ServerPlayer && !entity.level().isClientSide) {
			updateClient(entity, shield)
		}
	}

	override fun onUnequipShield(entity: LivingEntity, shield: SuperiorShieldCap) {
		shield.reset()
		if (entity is ServerPlayer && !entity.level().isClientSide) {
			updateClient(entity, shield)
		}
	}

	override fun calculateShieldAttributes(stack: ItemStack): SuperiorShieldAttributes {
		var capacityAttribute = capacity
		var rateAttribute = rate
		var delayAttribute = delay

		EnchantmentHelper.getEnchantments(stack).forEach { (enchantment, level) ->
			if (enchantment is AttributeProvider) {
				val shieldAttributeModifiers = enchantment.shieldAttributeModifiers(level)
				capacityAttribute += shieldAttributeModifiers.capacity
				rateAttribute -= (shieldAttributeModifiers.rechargeRateMultiplier * rateAttribute.toDouble()).roundToInt()
				delayAttribute -= shieldAttributeModifiers.rechargeDelay
			}
		}

		return SuperiorShieldAttributes(
			capacityAttribute,
			rateAttribute,
			delayAttribute
		)
	}
}