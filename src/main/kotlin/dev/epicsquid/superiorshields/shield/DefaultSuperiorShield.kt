package dev.epicsquid.superiorshields.shield

import dev.epicsquid.superiorshields.capability.SuperiorShieldCap
import dev.epicsquid.superiorshields.capability.absorbDamage
import dev.epicsquid.superiorshields.config.SuperiorShieldConfigItem
import dev.epicsquid.superiorshields.network.NetworkHandler
import dev.epicsquid.superiorshields.network.SuperiorShieldUpdatePacket
import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import net.minecraftforge.network.PacketDistributor

abstract class DefaultSuperiorShield(name: String) : SuperiorShield {

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
			// TODO trigger damage effect
			if (damage >= shield.hp) {
				// TODO trigger shield empty effect
			}
		}
		val damageLeft = shield.absorbDamage(damage.toInt()).toFloat()

		// Check if we should update a client player
		if (entity is ServerPlayer) {
			updateClient(entity, shield)
		}

		return damageLeft
	}

	private fun updateClient(player: ServerPlayer, shield: SuperiorShieldCap) {
		NetworkHandler.CHANNEL.send(
			PacketDistributor.PLAYER.with { player },
			SuperiorShieldUpdatePacket(shield.hp)
		)
	}
}