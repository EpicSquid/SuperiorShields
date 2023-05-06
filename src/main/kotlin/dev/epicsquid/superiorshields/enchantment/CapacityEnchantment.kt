package dev.epicsquid.superiorshields.enchantment

import dev.epicsquid.superiorshields.effects.EffectHandler
import net.minecraft.world.item.enchantment.EnchantmentCategory

class CapacityEnchantment(
	rarity: Rarity,
	category: EnchantmentCategory,
	effectHandler: EffectHandler
) : SuperiorShieldEnchantment(rarity, category, effectHandler) {

	override fun getMaxLevel(): Int = 3

	override fun getMinCost(pLevel: Int): Int = 5 + (pLevel - 1) * 8

	override fun getMaxCost(pLevel: Int): Int = super.getMinCost(pLevel) + 50

}