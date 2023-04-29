package dev.epicsquid.superiorshields.config

import net.minecraftforge.common.ForgeConfigSpec

object Config {

	private val configSpecPair = ForgeConfigSpec.Builder().configure {

	}

	val SHIELDS_CONFIG = configSpecPair.left
	val SHIELDS_CONFIG_SPEC = configSpecPair.right
}