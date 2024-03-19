package dev.epicsquid.superiorshields.shield

import cofh.core.util.helpers.AugmentableHelper
import cofh.lib.util.constants.NBTTags
import dev.epicsquid.superiorshields.config.SuperiorShieldStats
import net.minecraft.world.item.ItemStack
import kotlin.math.roundToInt

class ThermalSuperiorShield(
	config: SuperiorShieldStats
) : EnergySuperiorShield(config) {

	override fun calculateShieldAttributes(stack: ItemStack): SuperiorShieldAttributes {
		val defaultAttributes = super.calculateShieldAttributes(stack)

		val baseModLevel = baseModLevel(stack)

		return SuperiorShieldAttributes(
			(defaultAttributes.capacity + (2.0f * (baseModLevel - 1)).roundToInt()),
			(defaultAttributes.rate - (defaultAttributes.rate * (0.15 * (baseModLevel - 1)))).roundToInt(),
			(defaultAttributes.delay - (defaultAttributes.delay * (0.15 * (baseModLevel - 1)))).roundToInt()
		)
	}

	fun baseModLevel(stack: ItemStack): Float =
		AugmentableHelper.getPropertyWithDefault(stack, NBTTags.TAG_AUGMENT_BASE_MOD, 1.0f)
}