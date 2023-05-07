package dev.epicsquid.superiorshields.shield

import dev.epicsquid.superiorshields.config.Config
import dev.epicsquid.superiorshields.config.SuperiorShieldStats
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import vazkii.botania.api.mana.ManaItemHandler

sealed class BotaniaSuperiorShield(
	name: String,
	config: SuperiorShieldStats
) : DurabilitySuperiorShield(name, config) {

	fun repairWithMana(stack: ItemStack, player: Player) {
		if (stack.isDamaged && ManaItemHandler.instance()
				.requestManaExact(stack, player, Config.SHIELDS_CONFIG.botaniaManaConsumption.get(), true)
		) {
			stack.damageValue--
		}
	}
}