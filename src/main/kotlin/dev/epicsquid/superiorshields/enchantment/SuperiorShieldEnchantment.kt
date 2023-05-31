package dev.epicsquid.superiorshields.enchantment

import dev.epicsquid.superiorshields.effects.EffectHandler
import dev.epicsquid.superiorshields.shield.SuperiorShield
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory

open class SuperiorShieldEnchantment(
	rarity: Rarity,
	category: EnchantmentCategory,
	effectHandler: EffectHandler
) : Enchantment(rarity, category, arrayOf()), EffectHandler by effectHandler {

	override fun canApplyAtEnchantingTable(stack: ItemStack): Boolean =
		stack.item is SuperiorShield
}