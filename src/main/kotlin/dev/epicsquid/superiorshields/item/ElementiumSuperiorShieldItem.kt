package dev.epicsquid.superiorshields.item

import com.google.common.collect.Multimap
import dev.epicsquid.superiorshields.shield.BotaniaManaSuperiorShield
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import top.theillusivec4.curios.api.SlotContext
import vazkii.botania.common.handler.PixieHandler
import java.util.*

class ElementiumSuperiorShieldItem(
	props: Properties,
	enchantmentValue: Int,
	type: BotaniaManaSuperiorShield,
	repairItem: () -> Ingredient?,
) : SuperiorShieldItem<BotaniaManaSuperiorShield>(props, enchantmentValue, type, repairItem) {

	override fun getAttributeModifiers(
		slotContext: SlotContext,
		uuid: UUID,
		stack: ItemStack
	): Multimap<Attribute, AttributeModifier>? {
		val attributes = super.getAttributeModifiers(slotContext, uuid, stack)
		attributes.put(PixieHandler.PIXIE_SPAWN_CHANCE, AttributeModifier(uuid, "Pixie Spawn Chance Boost", 0.25, ADDITION))
		return attributes
	}
}