package dev.epicsquid.superiorshields.enchantment

import com.google.common.collect.Multimap
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import top.theillusivec4.curios.api.SlotContext
import java.util.*

interface AttributeProvider {

	fun getAttributeModifiers(
		slotContext: SlotContext?,
		uuid: UUID?,
		level: Int
	): Multimap<Attribute, AttributeModifier>
}