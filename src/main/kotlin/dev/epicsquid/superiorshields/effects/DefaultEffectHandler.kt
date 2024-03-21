package dev.epicsquid.superiorshields.effects

import dev.epicsquid.superiorshields.effects.EffectTrigger.*

class DefaultEffectHandler(
	private val damage: ((Damage) -> Unit)? = null,
	private val empty: ((Empty) -> Unit)? = null,
	private val filled: ((Filled) -> Unit)? = null,
	private val full: ((Full) -> Unit)? = null,
	private val recharge: ((Recharge) -> Unit)? = null,
	private val continualEmpty: ((ContinualEmpty) -> Unit)? = null
) : EffectHandler {
	companion object {
		val NONE = DefaultEffectHandler()
	}

	override fun applyEffect(effectTrigger: EffectTrigger, scale: Int) {
		when (effectTrigger) {
			is Damage -> damage?.invoke(effectTrigger)
			is Empty -> empty?.invoke(effectTrigger)
			is Filled -> filled?.invoke(effectTrigger)
			is Full -> full?.invoke(effectTrigger)
			is Recharge -> recharge?.invoke(effectTrigger)
			is ContinualEmpty -> continualEmpty?.invoke(effectTrigger)
		}
	}
}