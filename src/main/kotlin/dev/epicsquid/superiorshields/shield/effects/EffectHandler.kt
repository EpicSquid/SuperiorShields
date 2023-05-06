package dev.epicsquid.superiorshields.shield.effects

import dev.epicsquid.superiorshields.shield.effects.EffectTrigger.*

interface EffectHandler {
	val damageEffects: List<(Damage) -> Unit>
	val fullEffects: List<(Full) -> Unit>
	val emptyEffects: List<(Empty) -> Unit>
	val rechargeEffects: List<(Recharge) -> Unit>
	val filledEffects: List<(Filled) -> Unit>
}