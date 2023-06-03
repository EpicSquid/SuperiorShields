package dev.epicsquid.superiorshields.utils

import net.minecraftforge.common.util.LazyOptional
import java.util.function.Supplier
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class LazyOptionalDelegate<V>(val lazyOpt: LazyOptional<V>) : ReadOnlyProperty<Any?, V?>, Supplier<V?>, () -> V? {
	override fun getValue(thisRef: Any?, property: KProperty<*>): V? = lazyOptionalValueOrNull()
	override fun get(): V? = lazyOptionalValueOrNull()
	override fun invoke(): V? = lazyOptionalValueOrNull()

	private fun lazyOptionalValueOrNull(): V? =
		if (lazyOpt.isPresent) {
			lazyOpt.orElseThrow { IllegalStateException("LazyOptional is present but has no value") }
		} else {
			null
		}
}

fun <V : Any> lazyOpt(
	supplier: () -> V
) = LazyOptionalDelegate(LazyOptional.of(supplier))