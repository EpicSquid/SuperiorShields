package dev.epicsquid.superiorshields.shield

import dev.epicsquid.superiorshields.capability.SuperiorShieldCap
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack

interface SuperiorShield {

	val capacity: Int

	val rate: Int

	val delay: Int

	/**
	 * Called when the shield is to be recharged
	 */
	fun rechargeShield(stack: ItemStack, entity: LivingEntity)

	/**
	 * Called when the shield is being used to block damage
	 */
	fun applyShield(entity: LivingEntity, stack: ItemStack, damage: Float, source: DamageSource): Float

	/**
	 * Called each tick for this shield
	 */
	fun shieldTick(entity: LivingEntity, shield: SuperiorShieldCap, stack: ItemStack)
}