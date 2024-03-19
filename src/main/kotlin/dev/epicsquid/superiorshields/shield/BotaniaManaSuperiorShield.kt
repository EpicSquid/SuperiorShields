package dev.epicsquid.superiorshields.shield

import dev.epicsquid.superiorshields.capability.SuperiorShieldCap
import dev.epicsquid.superiorshields.config.Config
import dev.epicsquid.superiorshields.config.SuperiorShieldStats
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import vazkii.botania.api.mana.ManaItemHandler

class BotaniaManaSuperiorShield(
	config: SuperiorShieldStats
) : DurabilitySuperiorShield(config) {

	private fun repairWithMana(stack: ItemStack, player: Player) {
		if (stack.isDamaged && ManaItemHandler.instance()
				.requestManaExact(stack, player, Config.SHIELDS_CONFIG.botaniaManaConsumption.get(), true)
		) {
			stack.damageValue--
		}
	}

	override fun shieldTick(entity: LivingEntity, shield: SuperiorShieldCap, stack: ItemStack) {
		super.shieldTick(entity, shield, stack)
		if (entity is Player) {
			repairWithMana(stack, entity)
		}
	}
}