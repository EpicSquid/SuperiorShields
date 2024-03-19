package dev.epicsquid.superiorshields.item

import dev.epicsquid.superiorshields.data.SuperiorShieldsLang
import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
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
	props: Properties = Properties(),
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
		if (!ItemStack.isSameItem(prevStack, stack)) {
			onEquipShield(slotContext.entity, slotContext.entity.shield, stack)
		}
	}

	override fun onUnequip(slotContext: SlotContext, newStack: ItemStack, stack: ItemStack) {
		if (!ItemStack.isSameItem(stack, newStack)) {
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
		val delayDisplay = delayAttribute / 20.0

		tooltip.apply {
			add(Component.translatable(SuperiorShieldsLang.BLANK))
			add(Component.translatable(SuperiorShieldsLang.EQUIP).withStyle(ChatFormatting.GRAY))
			add(
				TooltipUtils.withArgs(Component.translatable(SuperiorShieldsLang.HP), decimalFormat.format(capacityAttribute))
					.withStyle(ChatFormatting.DARK_GREEN)
			)
			add(
				TooltipUtils.withArgs(
					Component.translatable(SuperiorShieldsLang.RECHARGE_RATE),
					decimalFormat.format(rateDisplay)
				)
					.withStyle(ChatFormatting.DARK_GREEN)
			)
			add(
				TooltipUtils.withArgs(
					Component.translatable(SuperiorShieldsLang.RECHARGE_DELAY),
					decimalFormat.format(delayDisplay)
				)
					.withStyle(ChatFormatting.DARK_GREEN)
			)
		}
	}
}