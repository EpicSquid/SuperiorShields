package dev.epicsquid.superiorshields.enchantment

import dev.epicsquid.superiorshields.effects.EffectHandler
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory

class SpikeEnchantment(
	rarity: Rarity,
	category: EnchantmentCategory,
	effectHandler: EffectHandler
) : SuperiorShieldEnchantment(rarity, category, effectHandler) {

	override fun checkCompatibility(other: Enchantment): Boolean = other !is SpikeEnchantment

	override fun getMaxLevel(): Int = 2

	override fun getMinCost(level: Int): Int = 5 + 9 * (level - 1)

	override fun getMaxCost(level: Int): Int = super.getMinCost(level) + 15

}