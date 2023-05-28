package dev.epicsquid.superiorshields.compat

import com.tterrag.registrate.util.nullness.NonNullFunction
import dev.epicsquid.superiorshields.item.ElementiumSuperiorShieldItem
import dev.epicsquid.superiorshields.item.SuperiorShieldItem
import dev.epicsquid.superiorshields.shield.BotaniaManaSuperiorShield
import dev.epicsquid.superiorshields.shield.DurabilitySuperiorShield
import net.minecraft.world.item.Item
import net.minecraft.world.item.crafting.Ingredient
import net.minecraftforge.fml.ModList

object BotaniaCompat {

	private val loaded: Boolean by lazy { ModList.get().isLoaded("botania") }

	fun botaniaManaShieldBuilder(
		enchantmentValue: Int,
		type: DurabilitySuperiorShield,
		repairItem: () -> Ingredient?,
	): NonNullFunction<Item.Properties, SuperiorShieldItem<*>> =
		if (loaded) {
			LoadedOnly.botaniaManaShieldBuilder(enchantmentValue, type, repairItem)
		} else {
			NonNullFunction { props: Item.Properties ->
				SuperiorShieldItem(
					props = props,
					enchantmentValue = enchantmentValue,
					type = type,
					repairItem = repairItem
				)
			}
		}

	fun elementiumShieldBuilder(
		enchantmentValue: Int,
		type: DurabilitySuperiorShield,
		repairItem: () -> Ingredient?,
	): NonNullFunction<Item.Properties, SuperiorShieldItem<*>> =
		if (loaded) {
			LoadedOnly.elementiumShieldBuilder(enchantmentValue, type, repairItem)
		} else {
			NonNullFunction { props: Item.Properties ->
				SuperiorShieldItem(
					props = props,
					enchantmentValue = enchantmentValue,
					type = type,
					repairItem = repairItem
				)
			}
		}

	object LoadedOnly {
		fun botaniaManaShieldBuilder(
			enchantmentValue: Int,
			type: DurabilitySuperiorShield,
			repairItem: () -> Ingredient?,
		): NonNullFunction<Item.Properties, SuperiorShieldItem<*>> =
			NonNullFunction { props: Item.Properties ->
				SuperiorShieldItem(
					props = props,
					enchantmentValue = enchantmentValue,
					type = BotaniaManaSuperiorShield(
						type.name,
						type.config
					),
					repairItem = repairItem,
				)
			}

		fun elementiumShieldBuilder(
			enchantmentValue: Int,
			type: DurabilitySuperiorShield,
			repairItem: () -> Ingredient?,
		): NonNullFunction<Item.Properties, SuperiorShieldItem<*>> =
			NonNullFunction { props: Item.Properties ->
				ElementiumSuperiorShieldItem(
					props = props,
					enchantmentValue = enchantmentValue,
					type = BotaniaManaSuperiorShield(
						type.name,
						type.config
					),
					repairItem = repairItem
				)
			}
	}
}