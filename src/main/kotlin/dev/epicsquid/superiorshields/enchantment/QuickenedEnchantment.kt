package dev.epicsquid.superiorshields.enchantment

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import dev.epicsquid.superiorshields.config.Config
import dev.epicsquid.superiorshields.effects.EffectHandler
import dev.epicsquid.superiorshields.registry.AttributeRegistry
import dev.epicsquid.superiorshields.registry.EnchantmentRegistry.capacity
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.item.enchantment.EnchantmentCategory
import top.theillusivec4.curios.api.SlotContext
import java.util.*

class QuickenedEnchantment(
	rarity: Rarity,
	category: EnchantmentCategory,
	effectHandler: EffectHandler
) : SuperiorShieldEnchantment(rarity, category, effectHandler), AttributeProvider {
	companion object {
		private val QUICKENED_UUID = UUID.fromString("e66639a6-ea52-4c6a-8bdf-f3b71063e96c")
	}

	override fun getMaxLevel(): Int = 3

	override fun getAttributeModifiers(
		slotContext: SlotContext?,
		uuid: UUID?,
		level: Int
	): Multimap<Attribute, AttributeModifier> =
		ImmutableMultimap.builder<Attribute, AttributeModifier>().apply {
			val quickenRate = level * Config.SHIELDS_CONFIG.quickenRate.get()
			put(
				AttributeRegistry.shieldRate,
				AttributeModifier(QUICKENED_UUID, "Shield Recharge Rate", -quickenRate.toDouble(), AttributeModifier.Operation.ADDITION)
			)
		}.build()
}