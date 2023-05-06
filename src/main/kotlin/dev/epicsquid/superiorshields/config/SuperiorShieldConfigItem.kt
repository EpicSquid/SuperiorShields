package dev.epicsquid.superiorshields.config

import dev.epicsquid.superiorshields.shield.AbstractSuperiorShield
import dev.epicsquid.superiorshields.shield.SuperiorShield
import dev.epicsquid.superiorshields.utils.stack
import net.minecraftforge.common.ForgeConfigSpec

class SuperiorShieldConfigItem(
	builder: ForgeConfigSpec.Builder,
	type: AbstractSuperiorShield
) {

	val capacity: ForgeConfigSpec.ConfigValue<Double>
	val rate: ForgeConfigSpec.ConfigValue<Int>
	val delay: ForgeConfigSpec.ConfigValue<Int>

	init {
		builder.stack("${type.name}_shield") {
			capacity = comment("The amount of health the shield has. One point is half a shield icon (same as hearts.)")
				.define("capacity", type.capacity)
			rate = comment("The amount of ticks between each attempt to recharge the shield bar.")
				.define("recharge_rate", type.rate)
			delay = comment("The amount of ticks without taking damage before the shield begins to recharge.")
				.define("recharge_delay", type.delay)
		}
	}
}