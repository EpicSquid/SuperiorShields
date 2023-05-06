package dev.epicsquid.superiorshields.enchantment

import dev.epicsquid.superiorshields.shield.SuperiorShield
import dev.epicsquid.superiorshields.shield.effects.EffectTrigger
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory

open class SuperiorShieldEnchantment(
	rarity: Rarity,
	category: EnchantmentCategory
) : Enchantment(rarity, category, arrayOf()) {

	override fun canApplyAtEnchantingTable(stack: ItemStack): Boolean =
		stack.item is SuperiorShield

	val effect: ((EffectTrigger) -> Unit)? = null
}