package dev.epicsquid.superiorshields.enchantment

import dev.epicsquid.superiorshields.effects.EffectHandler
import net.minecraft.world.item.enchantment.EnchantmentCategory

class CuringEnchantment(
	rarity: Rarity,
	category: EnchantmentCategory,
	effectHandler: EffectHandler
) : SuperiorShieldEnchantment(rarity, category, effectHandler) {

	override fun getMaxLevel(): Int = 1

	override fun getMinCost(level: Int): Int = 1

	override fun getMaxCost(level: Int): Int = super.getMinCost(level) + 40

}