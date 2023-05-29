package dev.epicsquid.superiorshields.compat

import com.tterrag.registrate.util.nullness.NonNullFunction
import dev.epicsquid.superiorshields.item.SoulStainedSuperiorShieldItem
import dev.epicsquid.superiorshields.item.SpiritHunterSuperiorShieldItem
import dev.epicsquid.superiorshields.item.SuperiorShieldItem
import dev.epicsquid.superiorshields.shield.DurabilitySuperiorShield
import net.minecraft.world.item.Item
import net.minecraft.world.item.crafting.Ingredient
import net.minecraftforge.fml.ModList

object MalumCompat {

	private val loaded: Boolean by lazy { ModList.get().isLoaded("malum") }

	fun spiritHunterShieldBuilder(
		enchantmentValue: Int,
		type: DurabilitySuperiorShield,
		repairItem: () -> Ingredient?,
	): NonNullFunction<Item.Properties, SuperiorShieldItem<DurabilitySuperiorShield>> =
		if (loaded) {
			LoadedOnly.spiritHunterShieldBuilder(enchantmentValue, type, repairItem)
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

	fun soulStainedShieldBuilder(
		enchantmentValue: Int,
		type: DurabilitySuperiorShield,
		repairItem: () -> Ingredient?,
	): NonNullFunction<Item.Properties, SuperiorShieldItem<DurabilitySuperiorShield>> =
		if (loaded) {
			LoadedOnly.soulStainedShieldBuilder(enchantmentValue, type, repairItem)
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
		fun spiritHunterShieldBuilder(
			enchantmentValue: Int,
			type: DurabilitySuperiorShield,
			repairItem: () -> Ingredient?,
		): NonNullFunction<Item.Properties, SuperiorShieldItem<DurabilitySuperiorShield>> =
			NonNullFunction { props: Item.Properties ->
				SpiritHunterSuperiorShieldItem(
					props = props,
					enchantmentValue = enchantmentValue,
					type = type,
					repairItem = repairItem,
				)
			}

		fun soulStainedShieldBuilder(
			enchantmentValue: Int,
			type: DurabilitySuperiorShield,
			repairItem: () -> Ingredient?,
		): NonNullFunction<Item.Properties, SuperiorShieldItem<DurabilitySuperiorShield>> =
			NonNullFunction { props: Item.Properties ->
				SoulStainedSuperiorShieldItem(
					props = props,
					enchantmentValue = enchantmentValue,
					type = type,
					repairItem = repairItem
				)
			}
	}
}