package dev.epicsquid.superiorshields.compat

import dev.epicsquid.superiorshields.item.ElementiumSuperiorShieldItem
import dev.epicsquid.superiorshields.item.SuperiorShieldItem
import dev.epicsquid.superiorshields.shield.BotaniaManaSuperiorShield
import dev.epicsquid.superiorshields.shield.DurabilitySuperiorShield
import net.minecraft.world.item.Item.Properties
import net.minecraft.world.item.crafting.Ingredient
import net.minecraftforge.fml.ModList

object BotaniaCompat {

	private val loaded: Boolean by lazy { ModList.get().isLoaded("botania") }

	fun botaniaManaShieldBuilder(
		props: Properties,
		enchantmentValue: Int,
		type: DurabilitySuperiorShield,
		repairItem: () -> Ingredient?,
	): SuperiorShieldItem<*> =
		if (loaded) {
			LoadedOnly.botaniaManaShieldBuilder(props, enchantmentValue, type, repairItem)
		} else {
			SuperiorShieldItem(
				props = props,
				enchantmentValue = enchantmentValue,
				type = type,
				repairItem = repairItem
			)
		}

	fun elementiumShieldBuilder(
		props: Properties,
		enchantmentValue: Int,
		type: DurabilitySuperiorShield,
		repairItem: () -> Ingredient?,
	): SuperiorShieldItem<*> =
		if (loaded) {
			LoadedOnly.elementiumShieldBuilder(props, enchantmentValue, type, repairItem)
		} else {
			SuperiorShieldItem(
				props = props,
				enchantmentValue = enchantmentValue,
				type = type,
				repairItem = repairItem
			)
		}

	object LoadedOnly {
		fun botaniaManaShieldBuilder(
			props: Properties,
			enchantmentValue: Int,
			type: DurabilitySuperiorShield,
			repairItem: () -> Ingredient?,
		): SuperiorShieldItem<*> =
			SuperiorShieldItem(
				props = props,
				enchantmentValue = enchantmentValue,
				type = BotaniaManaSuperiorShield(
					type.config
				),
				repairItem = repairItem,
			)

		fun elementiumShieldBuilder(
			props: Properties,
			enchantmentValue: Int,
			type: DurabilitySuperiorShield,
			repairItem: () -> Ingredient?,
		): SuperiorShieldItem<*> =
			ElementiumSuperiorShieldItem(
				props = props,
				enchantmentValue = enchantmentValue,
				type = BotaniaManaSuperiorShield(
					type.config
				),
				repairItem = repairItem
			)

	}
}