package dev.epicsquid.superiorshields.utils

import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.chat.contents.TranslatableContents

object TooltipUtils {
	fun withArgs(component: MutableComponent, vararg args: Any?): MutableComponent =
		if (component.contents is TranslatableContents) {
			Component.translatable((component.contents as TranslatableContents).key, *args)
		} else component
}