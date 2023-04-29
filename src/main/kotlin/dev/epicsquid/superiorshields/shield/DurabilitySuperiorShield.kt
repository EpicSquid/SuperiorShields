package dev.epicsquid.superiorshields.shield

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack

open class DurabilitySuperiorShield(name: String) : SuperiorShield(name) {

	fun damageItem(stack: ItemStack, player: Player) {
		stack.hurtAndBreak(1, player) {
			it.broadcastBreakEvent(EquipmentSlot.OFFHAND)
		}
	}
}