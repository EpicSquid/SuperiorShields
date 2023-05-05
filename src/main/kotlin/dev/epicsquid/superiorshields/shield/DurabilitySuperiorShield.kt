package dev.epicsquid.superiorshields.shield

import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack

open class DurabilitySuperiorShield(
	val name: String
) : DefaultSuperiorShield(name) {

	private fun damageItem(stack: ItemStack, player: Player) {
		stack.hurtAndBreak(1, player) {
			it.broadcastBreakEvent(EquipmentSlot.OFFHAND)
		}
	}

	override fun rechargeShield(stack: ItemStack, player: Player) {
		if (player.shield.hp < capacity) {
			damageItem(stack, player)
			player.shield.hp++
		}
	}
}