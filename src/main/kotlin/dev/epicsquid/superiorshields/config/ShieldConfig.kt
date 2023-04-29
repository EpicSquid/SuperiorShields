package dev.epicsquid.superiorshields.config

import dev.epicsquid.superiorshields.shield.SuperiorShieldType
import net.minecraftforge.common.ForgeConfigSpec

class ShieldConfig(
	builder: ForgeConfigSpec.Builder,
	type: SuperiorShieldType
) {

	val capacity: ForgeConfigSpec.ConfigValue<Double>
	val rate: ForgeConfigSpec.ConfigValue<Int>
	val delay: ForgeConfigSpec.ConfigValue<Int>

	init {
		// TODO add name
		builder.push("${type.name}_shield")
		capacity =
			builder.comment("The amount of health the shield has. One point is half a shield icon (same as hearts.)")
				.define("capacity", type.defaultCapacity)
		rate = builder.comment("The amount of ticks between each attempt to recharge the shield bar.")
			.define("recharge_rate", type.defaultRate)
		delay = builder.comment("The amount of ticks without taking damage before the shield begins to recharge.")
			.define("recharge_delay", type.defaultDelay)
	}
}