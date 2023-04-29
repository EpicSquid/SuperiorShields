package dev.epicsquid.superiorshields.shield

import dev.epicsquid.superiorshields.config.ShieldConfig

sealed class SuperiorShieldType(
	val name: String,
	val defaultCapacity: Double,
	val defaultRate: Int,
	val defaultDelay: Int
) : SuperiorShield {

	private val config: ShieldConfig
		get() = TODO()

	override val capacity: Double
		get() = config.capacity.get()

	override val rate: Int
		get() = config.rate.get()

	override val delay: Int
		get() = config.delay.get()
}