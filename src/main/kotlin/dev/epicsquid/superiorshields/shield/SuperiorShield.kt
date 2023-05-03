package dev.epicsquid.superiorshields.shield

import dev.epicsquid.superiorshields.config.SuperiorShieldConfigItem
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack

abstract class SuperiorShield(val name: String) {

	private val config: SuperiorShieldConfigItem
		get() = TODO()

	val capacity: Double
		get() = config.capacity.get()

	val rate: Int
		get() = config.rate.get()

	val delay: Int
		get() = config.delay.get()

	abstract fun rechargeShield(stack: ItemStack, player: Player)
}