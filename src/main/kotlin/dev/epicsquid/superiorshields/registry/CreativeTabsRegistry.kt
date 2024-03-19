package dev.epicsquid.superiorshields.registry

import dev.epicsquid.superiorshields.SuperiorShields.MODID
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraftforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.forge.registerObject

object CreativeTabsRegistry {

	val REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID)

	val tab by REGISTRY.registerObject("superior_shields") {
		CreativeModeTab.builder()
			.title(Component.translatable("itemGroup.$MODID"))
			.icon { ItemRegistry.ironShield.defaultInstance }
			.displayItems { _, output ->
				ItemRegistry.REGISTRY.entries.forEach {
					output.accept(it.get().defaultInstance)
				}
			}
			.build()
	}
}