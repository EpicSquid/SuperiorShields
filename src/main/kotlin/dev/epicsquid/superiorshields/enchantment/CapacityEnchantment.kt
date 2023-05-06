package dev.epicsquid.superiorshields.enchantment

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import dev.epicsquid.superiorshields.config.Config
import dev.epicsquid.superiorshields.effects.EffectHandler
import dev.epicsquid.superiorshields.registry.AttributeRegistry
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
	companion object {
		private val CAPACITY_UUID = UUID.fromString("09712a2d-2bcd-45af-9edc-a8a7d247546e")
	}

	override fun getMaxLevel(): Int = 3

	override fun getMinCost(pLevel: Int): Int = 5 + (pLevel - 1) * 8

	override fun getMaxCost(pLevel: Int): Int = super.getMinCost(pLevel) + 50

	override fun getAttributeModifiers(
		slotContext: SlotContext?,
		uuid: UUID?,
		level: Int
	): Multimap<Attribute, AttributeModifier> =
		ImmutableMultimap.builder<Attribute, AttributeModifier>().apply {
			val capacity = level * Config.SHIELDS_CONFIG.shieldCapacityIncrease.get()
			put(
				AttributeRegistry.shieldCapacity,
				AttributeModifier(CAPACITY_UUID, "Shield Capacity", capacity.toDouble(), AttributeModifier.Operation.ADDITION)
			)
		}.build()
}