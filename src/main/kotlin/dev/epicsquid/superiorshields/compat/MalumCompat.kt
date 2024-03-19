package dev.epicsquid.superiorshields.compat

import dev.epicsquid.superiorshields.item.SoulStainedSuperiorShieldItem
import dev.epicsquid.superiorshields.item.SpiritHunterSuperiorShieldItem
import dev.epicsquid.superiorshields.item.SuperiorShieldItem
import dev.epicsquid.superiorshields.shield.DurabilitySuperiorShield
import net.minecraft.world.item.Item.Properties
import net.minecraft.world.item.crafting.Ingredient
import net.minecraftforge.fml.ModList

object MalumCompat {

	private val loaded: Boolean by lazy { ModList.get().isLoaded("malum") }

	fun spiritHunterShieldBuilder(
		props: Properties,
		enchantmentValue: Int,
		type: DurabilitySuperiorShield,
		repairItem: () -> Ingredient?,
	): SuperiorShieldItem<DurabilitySuperiorShield> =
		if (loaded) {
			LoadedOnly.spiritHunterShieldBuilder(props, enchantmentValue, type, repairItem)
		} else {
			SuperiorShieldItem(
				props = props,
				enchantmentValue = enchantmentValue,
				type = type,
				repairItem = repairItem
			)
		}

	fun soulStainedShieldBuilder(
		props: Properties,
		enchantmentValue: Int,
		type: DurabilitySuperiorShield,
		repairItem: () -> Ingredient?,
	): SuperiorShieldItem<DurabilitySuperiorShield> =
		if (loaded) {
			LoadedOnly.soulStainedShieldBuilder(props, enchantmentValue, type, repairItem)
		} else {
			SuperiorShieldItem(
				props = props,
				enchantmentValue = enchantmentValue,
				type = type,
				repairItem = repairItem
			)
		}

	object LoadedOnly {
		fun spiritHunterShieldBuilder(
			props: Properties,
			enchantmentValue: Int,
			type: DurabilitySuperiorShield,
			repairItem: () -> Ingredient?,
		): SuperiorShieldItem<DurabilitySuperiorShield> =
			SpiritHunterSuperiorShieldItem(
				props = props,
				enchantmentValue = enchantmentValue,
				type = type,
				repairItem = repairItem,
			)

		fun soulStainedShieldBuilder(
			props: Properties,
			enchantmentValue: Int,
			type: DurabilitySuperiorShield,
			repairItem: () -> Ingredient?,
		): SuperiorShieldItem<DurabilitySuperiorShield> =
			SoulStainedSuperiorShieldItem(
				props = props,
				enchantmentValue = enchantmentValue,
				type = type,
				repairItem = repairItem
			)
	}
}