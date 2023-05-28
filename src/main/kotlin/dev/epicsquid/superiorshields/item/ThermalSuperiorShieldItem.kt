package dev.epicsquid.superiorshields.item

import cofh.core.item.IAugmentableItem
import cofh.core.util.helpers.AugmentDataHelper
import cofh.core.util.helpers.AugmentableHelper
import cofh.lib.util.constants.NBTTags
import dev.epicsquid.superiorshields.capability.EnergyCapProvider
import dev.epicsquid.superiorshields.shield.EnergySuperiorShield
import dev.epicsquid.superiorshields.shield.ThermalSuperiorShield
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.item.ItemStack
import net.minecraftforge.common.capabilities.ICapabilityProvider
import kotlin.math.roundToInt

class ThermalSuperiorShieldItem(
	props: Properties,
	enchantmentValue: Int,
	private val type: ThermalSuperiorShield,
	private val maxEnergy: Int,
	barColor: Int
) : EnergySuperiorShieldItem<ThermalSuperiorShield>(props, enchantmentValue, type, maxEnergy, barColor),
	IAugmentableItem{

	override fun getAugmentSlots(augmentable: ItemStack): Int = 1

	override fun validAugment(augmentable: ItemStack, augment: ItemStack, augments: List<ItemStack>): Boolean {
		// TODO: allow energy augs
		val newType = AugmentDataHelper.getAugmentType(augment)
		// Allow base mods
		if (newType.isNotEmpty() && newType != NBTTags.TAG_AUGMENT_TYPE_UPGRADE) {
			return false
		}
		// No duplicates
		return augments.none { newType == AugmentDataHelper.getAugmentType(it) }
	}

	override fun updateAugmentState(augmentable: ItemStack, augments: List<ItemStack>) {
		// TODO: allow energy augs
		augmentable.getOrCreateTag().put(NBTTags.TAG_PROPERTIES, CompoundTag())
		augments.mapNotNull { AugmentDataHelper.getAugmentData(it) }.forEach { augmentData ->
			augmentable.getTagElement(NBTTags.TAG_PROPERTIES)?.apply {
				AugmentableHelper.setAttributeFromAugmentMax(this, augmentData, NBTTags.TAG_AUGMENT_BASE_MOD)
			}
		}
	}

	override fun initCapabilities(stack: ItemStack, nbt: CompoundTag?): ICapabilityProvider {
		val maxEnergyAugmented = (maxEnergy * type.baseModLevel(stack)).roundToInt()
		return EnergyCapProvider(maxEnergyAugmented, maxEnergyAugmented / 100, maxEnergyAugmented / 100, stack)
	}
}