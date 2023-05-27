package dev.epicsquid.superiorshields.enchantment

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import dev.epicsquid.superiorshields.config.Config
import dev.epicsquid.superiorshields.effects.EffectHandler
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.item.enchantment.EnchantmentCategory
import top.theillusivec4.curios.api.SlotContext
import java.util.*

class CapacityEnchantment(
	rarity: Rarity,
	category: EnchantmentCategory,
	effectHandler: EffectHandler
) : SuperiorShieldEnchantment(rarity, category, effectHandler), AttributeProvider {
	override fun getMaxLevel(): Int = 3

	override fun getMinCost(pLevel: Int): Int = 5 + (pLevel - 1) * 8

	override fun getMaxCost(pLevel: Int): Int = super.getMinCost(pLevel) + 50

	override fun shieldAttributeModifiers(level: Int): ShieldAttributeModifiers =
		ShieldAttributeModifiers(
			capacity = level * Config.SHIELDS_CONFIG.shieldCapacityIncrease.get()
		)
}