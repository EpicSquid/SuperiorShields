package dev.epicsquid.superiorshields.utils

import com.tterrag.registrate.util.entry.RegistryEntry
import java.util.function.Supplier
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class RegistryEntryDelegate<V>(val registryEntry: RegistryEntry<V>) : ReadOnlyProperty<Any, V>, Supplier<V>, () -> V {
	override fun getValue(thisRef: Any, property: KProperty<*>): V = registryEntry.get()
	override fun get(): V = registryEntry.get()
	override fun invoke(): V = registryEntry.get()
}

fun <V : Any> registryEntry(
	supplier: () -> RegistryEntry<V>
) = RegistryEntryDelegate(supplier())