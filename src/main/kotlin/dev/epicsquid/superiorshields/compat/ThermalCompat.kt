package dev.epicsquid.superiorshields.compat

import com.tterrag.registrate.util.nullness.NonNullFunction
import dev.epicsquid.superiorshields.item.EnergySuperiorShieldItem
import dev.epicsquid.superiorshields.item.ThermalSuperiorShieldItem
import dev.epicsquid.superiorshields.shield.EnergySuperiorShield
import dev.epicsquid.superiorshields.shield.ThermalSuperiorShield
import net.minecraft.world.item.Item
import net.minecraftforge.fml.ModList

object ThermalCompat {

	private val loaded: Boolean by lazy { ModList.get().isLoaded("thermal") }

	fun thermalItemBuilder(
		enchantmentValue: Int,
		type: EnergySuperiorShield,
		maxEnergy: Int,
		barColor: Int
	): NonNullFunction<Item.Properties, EnergySuperiorShieldItem<*>> =
		if (loaded) {
			LoadedOnly.thermalShieldBuilder(enchantmentValue, type, maxEnergy, barColor)
		} else {
			NonNullFunction { props: Item.Properties ->
				EnergySuperiorShieldItem(
					props,
					enchantmentValue,
					type,
					maxEnergy,
					barColor
				)
			}
		}

	object LoadedOnly {
		fun thermalShieldBuilder(
			enchantmentValue: Int,
			type: EnergySuperiorShield,
			maxEnergy: Int,
			barColor: Int
		): NonNullFunction<Item.Properties, EnergySuperiorShieldItem<*>> =
			NonNullFunction { props: Item.Properties ->
				ThermalSuperiorShieldItem(
					props = props,
					enchantmentValue = enchantmentValue,
					type = ThermalSuperiorShield(
						type.name,
						type.config
					),
					maxEnergy = maxEnergy,
					barColor = barColor
				)
			}
	}
}