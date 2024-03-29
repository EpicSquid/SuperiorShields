package dev.epicsquid.superiorshields.item

import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
import dev.epicsquid.superiorshields.registry.LangRegistry
import dev.epicsquid.superiorshields.shield.SuperiorShield
import dev.epicsquid.superiorshields.utils.TooltipUtils
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import top.theillusivec4.curios.api.SlotContext
import top.theillusivec4.curios.api.type.capability.ICurioItem
import java.text.DecimalFormat
import java.util.*

open class SuperiorShieldItem<T : SuperiorShield>(
	props: Properties,
	private val enchantmentValue: Int,
	private val type: T,
	private val repairItem: () -> Ingredient? = { null }
) : Item(props), ICurioItem, SuperiorShield by type {

	override fun getEnchantmentValue(stack: ItemStack?): Int {
		return enchantmentValue
	}

	override fun curioTick(slotContext: SlotContext, stack: ItemStack) {
		shieldTick(slotContext.entity, slotContext.entity.shield, stack)
	}

	override fun onEquip(slotContext: SlotContext, prevStack: ItemStack, stack: ItemStack) {
		if (!ItemStack.isSameIgnoreDurability(prevStack, stack)) {
			onEquipShield(slotContext.entity, slotContext.entity.shield, stack)
		}
	}

	override fun onUnequip(slotContext: SlotContext, newStack: ItemStack, stack: ItemStack) {
		if (!ItemStack.isSameIgnoreDurability(stack, newStack)) {
			onUnequipShield(slotContext.entity, slotContext.entity.shield)
		}
	}

	override fun canEquipFromUse(slotContext: SlotContext?, stack: ItemStack?): Boolean = true

	override fun isValidRepairItem(stack: ItemStack, repairCandidate: ItemStack): Boolean {
		val repairItem = repairItem() ?: return super.isValidRepairItem(stack, repairCandidate)
		return repairItem.test(repairCandidate) || super.isValidRepairItem(stack, repairCandidate)
	}

	@OnlyIn(Dist.CLIENT)
	override fun appendHoverText(
		stack: ItemStack,
		level: Level?,
		tooltip: MutableList<Component>,
		isAdvanced: TooltipFlag
	) {
		super.appendHoverText(stack, level, tooltip, isAdvanced)

		val decimalFormat = DecimalFormat()
		decimalFormat.maximumFractionDigits = 2

		val (capacityAttribute, rateAttribute, delayAttribute) = calculateShieldAttributes(stack)

		val rateDisplay = rateAttribute / 20.0f
		val delayDisplay = delayAttribute / 20.0f

		tooltip.apply {
			add(LangRegistry.BLANK)
			add(LangRegistry.EQUIP.withStyle(ChatFormatting.GRAY))
			add(
				TooltipUtils.withArgs(LangRegistry.HP, decimalFormat.format(capacityAttribute))
					.withStyle(ChatFormatting.DARK_GREEN)
			)
			add(
				TooltipUtils.withArgs(LangRegistry.RECHARGE_RATE, decimalFormat.format(rateDisplay))
					.withStyle(ChatFormatting.DARK_GREEN)
			)
			add(
				TooltipUtils.withArgs(LangRegistry.RECHARGE_DELAY, decimalFormat.format(delayDisplay))
					.withStyle(ChatFormatting.DARK_GREEN)
			)
		}
	}
}