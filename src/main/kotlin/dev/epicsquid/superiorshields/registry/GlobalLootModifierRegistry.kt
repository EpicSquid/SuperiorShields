package dev.epicsquid.superiorshields.registry

import com.mojang.serialization.Codec
import dev.epicsquid.superiorshields.SuperiorShields
import dev.epicsquid.superiorshields.data.ShieldDungeonLootModifier
import net.minecraftforge.common.loot.IGlobalLootModifier
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object GlobalLootModifierRegistry {

	val REGISTRY: DeferredRegister<Codec<out IGlobalLootModifier>> =
		DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, SuperiorShields.MODID)

	val shieldLootModifier by REGISTRY.registerObject("shield_loot_modifier") {
		ShieldDungeonLootModifier.CODEC.get()
	}
}