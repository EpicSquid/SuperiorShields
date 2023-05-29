package dev.epicsquid.superiorshields.item

import com.google.common.collect.Multimap
import com.sammy.malum.core.setup.content.AttributeRegistry
import dev.epicsquid.superiorshields.shield.BotaniaManaSuperiorShield
import dev.epicsquid.superiorshields.shield.DurabilitySuperiorShield
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import team.lodestar.lodestone.setup.LodestoneAttributeRegistry
import top.theillusivec4.curios.api.SlotContext
import vazkii.botania.common.handler.PixieHandler
import java.util.*

class SpiritHunterSuperiorShieldItem(
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
		attributes.put(LodestoneAttributeRegistry.MAGIC_PROFICIENCY.get(), AttributeModifier(uuid, "Magic Proficiency", 1.0, ADDITION))
		attributes.put(AttributeRegistry.SCYTHE_PROFICIENCY.get(), AttributeModifier(uuid, "Scythe Proficiency", 1.0, ADDITION))
		return attributes
	}
}