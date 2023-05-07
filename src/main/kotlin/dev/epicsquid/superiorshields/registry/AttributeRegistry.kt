package dev.epicsquid.superiorshields.registry

import dev.epicsquid.superiorshields.SuperiorShields
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.RangedAttribute
import net.minecraftforge.event.entity.EntityAttributeModificationEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object AttributeRegistry {
	val attributes: DeferredRegister<Attribute> =
		DeferredRegister.create(ForgeRegistries.ATTRIBUTES, SuperiorShields.MODID)

	val shieldCapacity by attributes.registerObject("shield_capacity") {
		RangedAttribute("attribute.name.${SuperiorShields.MODID}.shield_capacity", 0.0, 0.0, 40.0)
	}

	val shieldRate by attributes.registerObject("shield_rate") {
		RangedAttribute("attribute.name.${SuperiorShields.MODID}.shield_rate", 0.0, 0.0, 2400.0)
	}

	val shieldDelay by attributes.registerObject("shield_delay") {
		RangedAttribute("attribute.name.${SuperiorShields.MODID}.shield_delay", 0.0, 0.0, 2400.0)
	}

	@SubscribeEvent
	fun modifyEntityAttributes(event: EntityAttributeModificationEvent) {
		event.types.forEach {
			event.add(it, shieldCapacity)
			event.add(it, shieldRate)
			event.add(it, shieldDelay)
		}
	}
}