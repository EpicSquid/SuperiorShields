package dev.epicsquid.superiorshields.item

import dev.epicsquid.superiorshields.capability.EnergyCapProvider
import dev.epicsquid.superiorshields.registry.LangRegistry
import dev.epicsquid.superiorshields.shield.EnergySuperiorShield
import dev.epicsquid.superiorshields.utils.TooltipUtils
import net.minecraft.ChatFormatting.GRAY
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level
import net.minecraftforge.common.capabilities.ForgeCapabilities
import net.minecraftforge.common.capabilities.ICapabilityProvider
import thedarkcolour.kotlinforforge.forge.getCapabilityOrThrow
import java.text.DecimalFormat
import kotlin.math.roundToInt

sealed class EnergySuperiorShieldItem<T : EnergySuperiorShield>(
	props: Properties,
	enchantmentValue: Int,
	type: T,
	private val maxEnergy: Int,
	private val barColor: Int
) : SuperiorShieldItem<T>(props, enchantmentValue, type) {

	override fun getBarColor(stack: ItemStack): Int = barColor

	override fun isBarVisible(stack: ItemStack): Boolean = true

	override fun getBarWidth(stack: ItemStack): Int {
		val energyStorage = stack.getCapabilityOrThrow(ForgeCapabilities.ENERGY)
		return (energyStorage.energyStored * 13.0f / energyStorage.maxEnergyStored).roundToInt()
	}

	override fun initCapabilities(stack: ItemStack, nbt: CompoundTag?): ICapabilityProvider =
		EnergyCapProvider(maxEnergy, maxEnergy / 100, maxEnergy / 100, stack)

	override fun appendHoverText(
		stack: ItemStack,
		level: Level?,
		tooltip: MutableList<Component>,
		isAdvanced: TooltipFlag
	) {
		super.appendHoverText(stack, level, tooltip, isAdvanced)

		val energyStorage = stack.getCapabilityOrThrow(ForgeCapabilities.ENERGY)
		val decimalFormat = DecimalFormat().apply { maximumFractionDigits = 2 }

		tooltip.add(LangRegistry.BLANK)
		tooltip.add(
			TooltipUtils.withArgs(
				LangRegistry.ENERGY,
				decimalFormat.format(energyStorage.energyStored.toDouble() / 1000.0),
				decimalFormat.format(energyStorage.maxEnergyStored.toDouble() / 1000.0)
			).withStyle(GRAY)
		)
	}
}