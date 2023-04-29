package dev.epicsquid.superiorshields.shield

import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import vazkii.botania.api.mana.ManaItemHandler

sealed class BotaniaSuperiorShield(name: String) : SuperiorShield(name) {

	fun repairWithMana(stack: ItemStack, player: Player) {
		if (stack.isDamaged && ManaItemHandler.instance().requestManaExact(stack, player, 100, true)) {
			stack.damageValue--
		}
	}
}