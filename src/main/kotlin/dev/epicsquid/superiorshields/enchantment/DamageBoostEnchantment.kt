package dev.epicsquid.superiorshields.enchantment

import dev.epicsquid.superiorshields.effects.EffectHandler
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory

abstract class DamageBoostEnchantment(
	rarity: Rarity,
	category: EnchantmentCategory,
	effectHandler: EffectHandler
) : SuperiorShieldEnchantment(rarity, category, effectHandler) {

	override fun checkCompatibility(other: Enchantment): Boolean =
		other !is DamageBoostEnchantment

	abstract fun boostDamage(damage: Float): Float

	abstract fun shouldBoostDamage(entity: LivingEntity): Boolean
}