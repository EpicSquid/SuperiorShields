package dev.epicsquid.superiorshields.enchantment

interface AttributeProvider {

	fun shieldAttributeModifiers(level: Int): ShieldAttributeModifiers
}

data class ShieldAttributeModifiers(
	val capacity: Int = 0,
	val rechargeRateMultiplier: Int = 0,
	val rechargeDelay: Int = 0
)