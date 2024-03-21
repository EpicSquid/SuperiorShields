package dev.epicsquid.superiorshields.enchantment

import dev.epicsquid.superiorshields.capability.SuperiorShieldCap
import dev.epicsquid.superiorshields.effects.EffectHandler
import net.minecraft.world.item.enchantment.EnchantmentCategory

class AlwaysAmplifyEnchantment(
	rarity: Rarity,
	category: EnchantmentCategory,
	effectHandler: EffectHandler,
) : AmplifyEnchantment(rarity, category, effectHandler) {

	override fun isTreasureOnly(): Boolean = true

	override fun drainShield(shield: SuperiorShieldCap) {
	}

}