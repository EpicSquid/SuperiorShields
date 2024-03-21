package dev.epicsquid.superiorshields.enchantment

import dev.epicsquid.superiorshields.effects.EffectHandler
import net.minecraft.world.item.enchantment.EnchantmentCategory

class ContinualNovaEnchantment(
	rarity: Rarity,
	category: EnchantmentCategory,
	effectHandler: EffectHandler
) : NovaEnchantment(rarity, category, effectHandler) {

	override fun getMaxLevel(): Int = 1

	override fun isTreasureOnly(): Boolean = true
}