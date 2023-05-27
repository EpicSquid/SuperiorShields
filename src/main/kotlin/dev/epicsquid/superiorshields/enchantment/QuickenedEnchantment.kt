package dev.epicsquid.superiorshields.enchantment

import dev.epicsquid.superiorshields.config.Config
import dev.epicsquid.superiorshields.effects.EffectHandler
import net.minecraft.world.item.enchantment.EnchantmentCategory
import kotlin.math.roundToInt

class QuickenedEnchantment(
	rarity: Rarity,
	category: EnchantmentCategory,
	effectHandler: EffectHandler
) : SuperiorShieldEnchantment(rarity, category, effectHandler), AttributeProvider {
	override fun getMaxLevel(): Int = 3

	override fun shieldAttributeModifiers(level: Int): ShieldAttributeModifiers =
		ShieldAttributeModifiers(
			rechargeRateMultiplier = level.toDouble() * Config.SHIELDS_CONFIG.quickenMultiplier.get()
		)
}