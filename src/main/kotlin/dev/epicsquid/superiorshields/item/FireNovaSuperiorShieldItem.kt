package dev.epicsquid.superiorshields.item

import dev.epicsquid.superiorshields.registry.EnchantmentRegistry
import dev.epicsquid.superiorshields.shield.DurabilitySuperiorShield
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.enchantment.Enchantment
import java.util.*

class FireNovaSuperiorShieldItem(
	props: Properties,
	enchantmentValue: Int,
	type: DurabilitySuperiorShield,
	repairItem: () -> Ingredient?,
) : SuperiorShieldItem<DurabilitySuperiorShield>(props, enchantmentValue, type, repairItem) {

	override fun getAllEnchantments(stack: ItemStack): MutableMap<Enchantment, Int> =
		super.getAllEnchantments(stack).apply { put(EnchantmentRegistry.fireNova, 1) }

}