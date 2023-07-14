package dev.epicsquid.superiorshields.item

import com.google.common.collect.Multimap
import com.sammy.malum.registry.common.AttributeRegistry
import dev.epicsquid.superiorshields.shield.DurabilitySuperiorShield
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import team.lodestar.lodestone.registry.common.LodestoneAttributeRegistry
import top.theillusivec4.curios.api.SlotContext
import java.util.*

class SoulStainedSuperiorShieldItem(
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
		attributes.put(LodestoneAttributeRegistry.MAGIC_RESISTANCE.get(), AttributeModifier(uuid, "Magic Resistance", 1.0, ADDITION))
		attributes.put(AttributeRegistry.SOUL_WARD_CAP.get(), AttributeModifier(uuid, "Soul Ward Cap", 3.0, ADDITION))
		return attributes
	}
}