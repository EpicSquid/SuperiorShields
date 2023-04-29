package dev.epicsquid.superiorshields.config

import dev.epicsquid.superiorshields.utils.stack
import net.minecraftforge.common.ForgeConfigSpec

class SuperiorShieldsConfig(
	builder: ForgeConfigSpec.Builder
) {

	val botaniaManaConsumption: ForgeConfigSpec.ConfigValue<Int>
	val energyConsumption: ForgeConfigSpec.ConfigValue<Int>

	init {
		builder.stack("consumption") {
			comment("The amount of a resource to use for a given shield type.")
			botaniaManaConsumption = comment("The amount of botania mana to consume when recharging a single shield HP.")
				.define("botania_mana", 400)
			energyConsumption =
				comment("The amount of forge energy (FE) to consume when recharging a single shield HP.")
					.define("energy", 400)
		}
	}
}