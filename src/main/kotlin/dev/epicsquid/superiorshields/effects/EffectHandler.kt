package dev.epicsquid.superiorshields.effects

interface EffectHandler {
	/**
	 * Applies an effect for the given effect trigger
	 */
	fun applyEffect(effectTrigger: EffectTrigger, scale: Int = 0)
}