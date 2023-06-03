package dev.epicsquid.superiorshields.item

import com.google.common.collect.Multimap
import dev.epicsquid.superiorshields.shield.DurabilitySuperiorShield
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import top.theillusivec4.curios.api.SlotContext
import java.util.*

class ArmorSuperiorShieldItem(
	props: Properties,
	enchantmentValue: Int,
	type: DurabilitySuperiorShield,
	repairItem: () -> Ingredient?,
) : SuperiorShieldItem<DurabilitySuperiorShield>(props, enchantmentValue, type, repairItem) {

	override fun getAttributeModifiers(
		slotContext: SlotContext,
		uuid: UUID,
		stack: ItemStack
	): Multimap<Attribute, AttributeModifier>? {
		val attributes = super.getAttributeModifiers(slotContext, uuid, stack)
		attributes.put(Attributes.ARMOR, AttributeModifier(uuid, "Curio armor boost", 4.0, ADDITION))
		return attributes
	}
}