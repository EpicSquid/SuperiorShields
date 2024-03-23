package dev.epicsquid.superiorshields.config

import dev.epicsquid.squidink.config.stack
import net.minecraftforge.common.ForgeConfigSpec

class SuperiorShieldStats(
	name: String,
	builder: ForgeConfigSpec.Builder,
	capacityIn: Int,
	rateIn: Int,
	delayIn: Int,
) {

	val capacity: ForgeConfigSpec.ConfigValue<Int>
	val rate: ForgeConfigSpec.ConfigValue<Int>
	val delay: ForgeConfigSpec.ConfigValue<Int>

	init {
		builder.stack("${name}_shield") {
			capacity = comment("The amount of health the shield has. One point is half a shield icon (same as hearts.)")
				.define("capacity", capacityIn)
			rate = comment("The amount of ticks between each attempt to recharge the shield bar.")
				.define("recharge_rate", rateIn)
			delay = comment("The amount of ticks without taking damage before the shield begins to recharge.")
				.define("recharge_delay", delayIn)
		}
	}
}

fun ForgeConfigSpec.Builder.shield(name: String, capacity: Int, rate: Int, delay: Int) =
	SuperiorShieldStats(name, this, capacity, rate, delay)