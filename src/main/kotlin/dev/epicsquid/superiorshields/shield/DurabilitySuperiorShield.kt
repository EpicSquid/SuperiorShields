package dev.epicsquid.superiorshields.shield

import dev.epicsquid.superiorshields.config.SuperiorShieldStats
import dev.epicsquid.superiorshields.effects.DefaultEffectHandler
import dev.epicsquid.superiorshields.effects.EffectHandler
import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack

open class DurabilitySuperiorShield(
	name: String,
	config: SuperiorShieldStats,
	effectHandler: EffectHandler = DefaultEffectHandler.NONE
) : AbstractSuperiorShield(name, config, effectHandler) {

	private fun damageItem(stack: ItemStack, entity: LivingEntity) {
		stack.hurtAndBreak(1, entity) {
			it.broadcastBreakEvent(EquipmentSlot.OFFHAND)
		}
	}

	override fun rechargeShield(stack: ItemStack, entity: LivingEntity) {
		val shield = entity.shield
		if (shield.hp < shield.capacity) {
			damageItem(stack, entity)
			shield.hp++
		}
	}
}