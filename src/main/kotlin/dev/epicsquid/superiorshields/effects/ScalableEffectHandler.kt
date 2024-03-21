package dev.epicsquid.superiorshields.effects

import dev.epicsquid.superiorshields.effects.EffectTrigger.*

class ScalableEffectHandler(
	private val damage: ((Damage, Int) -> Unit)? = null,
	private val empty: ((Empty, Int) -> Unit)? = null,
	private val filled: ((Filled, Int) -> Unit)? = null,
	private val full: ((Full, Int) -> Unit)? = null,
	private val recharge: ((Recharge, Int) -> Unit)? = null,
	private val continualEmpty: ((ContinualEmpty, Int) -> Unit)? = null
) : EffectHandler {

	override fun applyEffect(effectTrigger: EffectTrigger, scale: Int) {
		when (effectTrigger) {
			is Damage -> damage?.invoke(effectTrigger, scale)
			is Empty -> empty?.invoke(effectTrigger, scale)
			is Filled -> filled?.invoke(effectTrigger, scale)
			is Full -> full?.invoke(effectTrigger, scale)
			is Recharge -> recharge?.invoke(effectTrigger, scale)
			is ContinualEmpty -> continualEmpty?.invoke(effectTrigger, scale)
		}
	}
}