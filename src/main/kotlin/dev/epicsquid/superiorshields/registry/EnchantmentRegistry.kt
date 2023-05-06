package dev.epicsquid.superiorshields.registry

import dev.epicsquid.superiorshields.SuperiorShields
import dev.epicsquid.superiorshields.effects.DefaultEffectHandler
import dev.epicsquid.superiorshields.effects.EffectHandler
import dev.epicsquid.superiorshields.enchantment.AmplifyEnchantment
import dev.epicsquid.superiorshields.enchantment.CapacityEnchantment
import dev.epicsquid.superiorshields.enchantment.RagingEnchantment
import dev.epicsquid.superiorshields.enchantment.SuperiorShieldEnchantment
import dev.epicsquid.superiorshields.shield.SuperiorShield
import dev.epicsquid.superiorshields.utils.registryEntry
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory

object EnchantmentRegistry {

	private val type = EnchantmentCategory.create("superior_shield") { it is SuperiorShield }

	private val registrate = SuperiorShields.registrate

	val capacity: CapacityEnchantment by registryEntry {
		registrate.enchantment<CapacityEnchantment>("capacity", type, factory(::CapacityEnchantment))
			.rarity(Enchantment.Rarity.COMMON)
			.lang("Shield Capacity")
			.register()
	}

	val raging: RagingEnchantment by registryEntry {
		registrate.enchantment<RagingEnchantment>("raging", type, factory(::RagingEnchantment))
			.rarity(Enchantment.Rarity.RARE)
			.lang("Raging")
			.register()
	}

	val amplify: AmplifyEnchantment by registryEntry {
		registrate.enchantment<AmplifyEnchantment>("amplify", type, factory(::AmplifyEnchantment))
			.rarity(Enchantment.Rarity.RARE)
			.lang("Amplify")
			.register()
	}

	private inline fun <reified T : SuperiorShieldEnchantment> factory(
		crossinline enchantmentConstructor: (Enchantment.Rarity, EnchantmentCategory, EffectHandler) -> T,
		effectHandler: EffectHandler = DefaultEffectHandler.NONE
	): (Enchantment.Rarity, EnchantmentCategory, Array<out EquipmentSlot>) -> T {
		return { rarity, type, _ -> enchantmentConstructor(rarity, type, effectHandler) }
	}
}