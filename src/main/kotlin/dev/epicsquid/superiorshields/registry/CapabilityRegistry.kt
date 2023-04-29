package dev.epicsquid.superiorshields.registry

import dev.epicsquid.superiorshields.SuperiorShields
import dev.epicsquid.superiorshields.capability.SuperiorShieldCap
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.LivingEntity
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.common.capabilities.CapabilityToken
import thedarkcolour.kotlinforforge.forge.getCapabilityOrThrow

object CapabilityRegistry {
	val SUPERIOR_SHIELD_CAP_ID = ResourceLocation(SuperiorShields.MODID, "superior_shield")

	val SUPERIOR_SHIELD_CAP: Capability<SuperiorShieldCap> =
		CapabilityManager.get(object : CapabilityToken<SuperiorShieldCap>() {})

	val LivingEntity.shield: SuperiorShieldCap
		get() = getCapabilityOrThrow(SUPERIOR_SHIELD_CAP)
}