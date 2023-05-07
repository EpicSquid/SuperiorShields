package dev.epicsquid.superiorshields.shield

import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack

open class DurabilitySuperiorShield(
	name: String
) : AbstractSuperiorShield(name) {

	private fun damageItem(stack: ItemStack, entity: LivingEntity) {
		stack.hurtAndBreak(1, entity) {
			it.broadcastBreakEvent(EquipmentSlot.OFFHAND)
		}
	}

	override fun rechargeShield(stack: ItemStack, entity: LivingEntity) {
		if (entity.shield.hp < capacity) {
			damageItem(stack, entity)
			entity.shield.hp++
		}
	}
}