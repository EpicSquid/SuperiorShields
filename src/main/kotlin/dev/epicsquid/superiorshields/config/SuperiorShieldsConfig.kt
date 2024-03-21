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

	val tinShield: SuperiorShieldStats
	val leadShield: SuperiorShieldStats
	val silverShield: SuperiorShieldStats
	val nickelShield: SuperiorShieldStats
	val bronzeShield: SuperiorShieldStats
	val electrumShield: SuperiorShieldStats
	val invarShield: SuperiorShieldStats
	val constantanShield: SuperiorShieldStats

	val lapisShield: SuperiorShieldStats
	val osmiumShield: SuperiorShieldStats
	val steelShield: SuperiorShieldStats
	val refinedGlowstoneShield: SuperiorShieldStats
	val refinedObsidianShield: SuperiorShieldStats

	val electricShield: SuperiorShieldStats
	val engineersShield: SuperiorShieldStats

	val fluxShield: SuperiorShieldStats

	val manasteelShield: SuperiorShieldStats
	val terrasteelShield: SuperiorShieldStats
	val elementiumShield: SuperiorShieldStats

	val soulStainedShield: SuperiorShieldStats
	val spiritHunterShield: SuperiorShieldStats

	val enchanterShield: SuperiorShieldStats

	val ironwoodShield: SuperiorShieldStats
	val steeleafShield: SuperiorShieldStats
	val knightmetalShield: SuperiorShieldStats
	val fieryShield: SuperiorShieldStats

	val darksteelShield: SuperiorShieldStats
	val endsteelShield: SuperiorShieldStats

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
				copperShield = shield("copper", 3, 80, 40)
				ironShield = shield("iron", 5, 80, 40)
				goldShield = shield("gold", 3, 20, 40)
				diamondShield = shield("diamond", 7, 60, 40)
				netheriteShield = shield("netherite", 8, 40, 40)
			}
			builder.stack("metal") {
				comment("Shields made from materials often present in a variety of mods")
				tinShield = shield("tin", 3, 40, 40)
				leadShield = shield("lead", 7, 40, 80)
				silverShield = shield("silver", 4, 40, 20)
				nickelShield = shield("nickel", 5, 40, 40)
				bronzeShield = shield("bronze", 7, 80, 40)
				electrumShield = shield("electrum", 6, 20, 10)
				invarShield = shield("invar", 8, 40, 40)
				constantanShield = shield("constantan", 5, 60, 20)
			}
		}
		builder.stack("mekanism_metals") {
			comment("Shields made from materials present in Mekanism")
			lapisShield = shield("lapis", 3, 20, 40)
			osmiumShield = shield("osmium", 4, 60, 40)
			steelShield = shield("steel", 8, 80, 100)
			refinedGlowstoneShield = shield("refined_glowstone", 6, 20, 60)
			refinedObsidianShield = shield("refined_obsidian", 12, 60, 40)
		}
		builder.stack("energy") {
			comment("Shields made from rechargable materials")
			electricShield = shield("electric", 6, 60, 60)
			engineersShield = shield("engineers", 8, 60, 80)
		}
		builder.stack("thermal") {
			comment("Thermal augmentable shields stats. Each one represents a tier based on the integral components")
			fluxShield = shield("flux", 5, 80, 40)
		}
		builder.stack("botania") {
			comment("Shields made from materials available in Botania")
			manasteelShield = shield("manasteel", 5, 80, 40)
			terrasteelShield = shield("terrasteel", 9, 60, 40)
			elementiumShield = shield("elementium", 7, 60, 20)
		}
		builder.stack("malum") {
			comment("Shields made from materials present in Malum")
			soulStainedShield = shield("soul_stained", 6, 60, 40)
			spiritHunterShield = shield("spirit_hunter", 4, 20, 50)
		}
		builder.stack("ars_nouveau") {
			comment("Shields made from materials present in Ars Noveau")
			enchanterShield = shield("enchanter", 5, 80, 40)
		}
		builder.stack("twilight_forest") {
			comment("Shields made from materials present in the Twilight Forest")
			ironwoodShield = shield("ironwood", 5, 40, 40)
			steeleafShield = shield("steeleaf", 9, 60, 100)
			knightmetalShield = shield("knightmetal", 6, 40, 40)
			fieryShield = shield("fiery", 7, 30, 60)
		}
		builder.stack("enderio") {
			comment("Shields made from materials present in Ender IO")
			darksteelShield = shield("darksteel", 8, 60, 40)
			endsteelShield = shield("endsteel", 10, 60, 40)
		}
	}
}