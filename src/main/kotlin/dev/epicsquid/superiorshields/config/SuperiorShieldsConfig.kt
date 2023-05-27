package dev.epicsquid.superiorshields.config

import dev.epicsquid.superiorshields.utils.stack
import net.minecraftforge.common.ForgeConfigSpec

class SuperiorShieldsConfig(
	builder: ForgeConfigSpec.Builder
) {

	val botaniaManaConsumption: ForgeConfigSpec.ConfigValue<Int>
	val energyConsumption: ForgeConfigSpec.ConfigValue<Int>
	val ragingDamageAdded: ForgeConfigSpec.ConfigValue<Double>
	val amplifyShieldDrain: ForgeConfigSpec.ConfigValue<Int>
	val amplifyDamageMultiplier: ForgeConfigSpec.ConfigValue<Double>
	val shieldCapacityIncrease: ForgeConfigSpec.ConfigValue<Int>
	val quickenMultiplier: ForgeConfigSpec.ConfigValue<Double>
	val novaEffectDuration: ForgeConfigSpec.ConfigValue<Int>
	val novaRange: ForgeConfigSpec.ConfigValue<Double>
	val spikeEffectDuration: ForgeConfigSpec.ConfigValue<Int>

	val copperShield: SuperiorShieldStats
	val ironShield: SuperiorShieldStats
	val goldShield: SuperiorShieldStats
	val diamondShield: SuperiorShieldStats
	val netheriteShield: SuperiorShieldStats

	init {
		builder.stack("consumption") {
			comment("The amount of a resource to use for a given shield type.")
			botaniaManaConsumption = comment("The amount of botania mana to consume when recharging a single shield HP.")
				.define("botania_mana", 400)
			energyConsumption =
				comment("The amount of forge energy (FE) to consume when recharging a single shield HP.")
					.define("energy", 400)
		}

		builder.stack("enchantments") {
			comment("Configuration for power of various enchantments in the mod.");
			ragingDamageAdded =
				comment("The damage added to attacks while shields are depleted with the raging enchantment.")
					.define("raging_damage_added", 2.0)
			amplifyDamageMultiplier =
				comment("The damage multiplier for attacks of the amplify enchantment.")
					.define("amplify_damage_multiplier", 1.5)
			amplifyShieldDrain =
				comment("The amount of shield HP drained when the amplify enchantment is triggered.")
					.define("amplify_shield_drain", 3)
			shieldCapacityIncrease =
				comment("The amount of shield HP added for reach level of shield capacity enchantment.")
					.define("shield_capacity_increase", 2)
			quickenMultiplier =
				comment("The multiplier for the recharge rate of the shield applied by the quickened enchantment.")
					.define("quicken_multiplier", 0.2)
			novaEffectDuration =
				comment("The duration of the effect applied by the triggered nova enchantment.")
					.define("nova_effect_duration", 5)
			novaRange =
				comment("The radius of a nova enchantment's triggered effect.")
					.define("nova_range", 1.5)
			spikeEffectDuration =
				comment("The duration of the effect applied when a mob attacks you.")
					.define("spike_effect_duration", 2);
		}

		builder.stack("shields") {
			builder.stack("vanilla") {
				comment("Shields made from materials often present in a variety of mods")
				copperShield = builder.shield("copper", 3, 80, 40)
				ironShield = builder.shield("iron", 5, 80, 40)
				goldShield = builder.shield("gold", 3, 20, 40)
				diamondShield = builder.shield("diamond", 7, 60, 40)
				netheriteShield = builder.shield("netherite", 8, 40, 40)
			}
		}
	}
}