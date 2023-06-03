package dev.epicsquid.superiorshields.shield

import dev.epicsquid.superiorshields.config.Config
import dev.epicsquid.superiorshields.config.SuperiorShieldStats
import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import net.minecraftforge.common.capabilities.ForgeCapabilities
import thedarkcolour.kotlinforforge.forge.getCapabilityOrThrow

open class EnergySuperiorShield(
	name: String,
	config: SuperiorShieldStats
) : AbstractSuperiorShield(name, config) {

	override fun rechargeShield(stack: ItemStack, entity: LivingEntity) {
		val shield = entity.shield
		if (shield.hp < shield.capacity) {
			val energyStorage = stack.getCapabilityOrThrow(ForgeCapabilities.ENERGY)
			val energyToConsume = Config.SHIELDS_CONFIG.energyConsumption.get()
			if (energyStorage.energyStored >= energyToConsume) {
				energyStorage.extractEnergy(energyToConsume, false)
				shield.hp++
			}
		}
	}
}