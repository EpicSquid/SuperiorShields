package dev.epicsquid.superiorshields.item

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import dev.epicsquid.superiorshields.registry.AttributeRegistry
import dev.epicsquid.superiorshields.shield.SuperiorShield
import dev.epicsquid.superiorshields.shield.SuperiorShieldType
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import top.theillusivec4.curios.api.SlotContext
import top.theillusivec4.curios.api.type.capability.ICurioItem
import java.util.*

class SuperiorShieldItem<T : SuperiorShieldType>(
	props: Properties,
	type: T
) : Item(props), ICurioItem, SuperiorShield by type {
	companion object {
		val CAPACITY_UUID: UUID = UUID.fromString("e3c5b4a0-3f1a-4b1a-9b1a-5a4b1a3f1a3f")
		val RATE_UUID: UUID = UUID.fromString("e3c5b4a0-3f1a-4b1a-9b1a-5a4b1a3f1a3d")
		val DELAY_UUID: UUID = UUID.fromString("e3c5b4a0-3f1a-4b1a-9b1a-5a4b1a3f1a3e")
	}

	private val defaultModifiers: Multimap<Attribute, AttributeModifier>

	init {
		defaultModifiers = ImmutableMultimap.builder<Attribute, AttributeModifier>().apply {
			put(
				AttributeRegistry.shieldCapacity,
				AttributeModifier(CAPACITY_UUID, "Shield Capacity", capacity, AttributeModifier.Operation.ADDITION)
			)
			put(
				AttributeRegistry.shieldRate,
				AttributeModifier(RATE_UUID, "Shield Rate", rate.toDouble(), AttributeModifier.Operation.ADDITION)
			)
			put(
				AttributeRegistry.shieldDelay,
				AttributeModifier(DELAY_UUID, "Shield Delay", delay.toDouble(), AttributeModifier.Operation.ADDITION)
			)
		}.build()
	}

	override fun getAttributeModifiers(
		slotContext: SlotContext?,
		uuid: UUID?,
		stack: ItemStack?
	): Multimap<Attribute, AttributeModifier> {
		return defaultModifiers
	}

	override fun canEquipFromUse(slotContext: SlotContext?, stack: ItemStack?): Boolean = true
}