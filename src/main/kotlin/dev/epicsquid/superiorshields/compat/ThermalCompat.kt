package dev.epicsquid.superiorshields.compat

import dev.epicsquid.superiorshields.item.EnergySuperiorShieldItem
import dev.epicsquid.superiorshields.item.ThermalSuperiorShieldItem
import dev.epicsquid.superiorshields.shield.EnergySuperiorShield
import dev.epicsquid.superiorshields.shield.ThermalSuperiorShield
import net.minecraftforge.fml.ModList

object ThermalCompat {

	private val loaded: Boolean by lazy { ModList.get().isLoaded("thermal") }

	fun thermalShield(
		enchantmentValue: Int,
		type: EnergySuperiorShield,
		maxEnergy: Int,
		barColor: Int
	): EnergySuperiorShieldItem<*> =
		if (loaded) {
			LoadedOnly.thermalShieldBuilder(enchantmentValue, type, maxEnergy, barColor)
		} else {
			EnergySuperiorShieldItem(
				enchantmentValue = enchantmentValue,
				type = type,
				maxEnergy = maxEnergy,
				barColor = barColor
			)
		}

	object LoadedOnly {
		fun thermalShieldBuilder(
			enchantmentValue: Int,
			type: EnergySuperiorShield,
			maxEnergy: Int,
			barColor: Int
		): EnergySuperiorShieldItem<*> =
			ThermalSuperiorShieldItem(
				enchantmentValue = enchantmentValue,
				type = ThermalSuperiorShield(
					type.config
				),
				maxEnergy = maxEnergy,
				barColor = barColor
			)

	}
}