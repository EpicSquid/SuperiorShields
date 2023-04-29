package dev.epicsquid.superiorshields.utils

import net.minecraftforge.common.ForgeConfigSpec
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun ForgeConfigSpec.Builder.stack(path: String, block: ForgeConfigSpec.Builder.() -> Unit) {
	contract {
		callsInPlace(block, InvocationKind.EXACTLY_ONCE)
	}
	push(path)
	block()
	pop()
}