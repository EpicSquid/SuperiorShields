package dev.epicsquid.superiorshields.enchantment

import dev.epicsquid.superiorshields.effects.EffectHandler
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory

class NovaEnchantment(
	rarity: Rarity,
	category: EnchantmentCategory,
	effectHandler: EffectHandler
) : SuperiorShieldEnchantment(rarity, category, effectHandler) {

	override fun checkCompatibility(other: Enchantment): Boolean = other !is NovaEnchantment

	override fun getMaxLevel(): Int = 3

	override fun getMinCost(level: Int): Int = 10 + 20 * (level - 1)

	override fun getMaxCost(level: Int): Int = super.getMinCost(level) + 50

}