package dev.epicsquid.superiorshields.registry

import dev.epicsquid.superiorshields.SuperiorShields
import dev.epicsquid.superiorshields.capability.SuperiorShieldCap
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.LivingEntity
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.common.capabilities.CapabilityToken
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import thedarkcolour.kotlinforforge.forge.getCapabilityOrThrow

@EventBusSubscriber(modid = SuperiorShields.MODID, bus = EventBusSubscriber.Bus.FORGE)
object CapabilityRegistry {
	val SUPERIOR_SHIELD_CAP_ID = ResourceLocation(SuperiorShields.MODID, "superior_shield")

	val SUPERIOR_SHIELD_CAP: Capability<SuperiorShieldCap> =
		CapabilityManager.get(object : CapabilityToken<SuperiorShieldCap>() {})

	val LivingEntity.shield: SuperiorShieldCap
		get() = getCapabilityOrThrow(SUPERIOR_SHIELD_CAP)

	@SubscribeEvent
	fun onRegisterCapabilities(event: RegisterCapabilitiesEvent) {
		event.register(SuperiorShieldCap::class.java)
	}
}