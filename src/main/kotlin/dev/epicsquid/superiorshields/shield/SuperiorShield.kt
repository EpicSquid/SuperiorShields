package dev.epicsquid.superiorshields.shield

import dev.epicsquid.superiorshields.capability.SuperiorShieldCap
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import top.theillusivec4.curios.api.SlotContext

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

	/**
	 * Called when the shield is equipped
	 */
	fun onEquipShield(entity: LivingEntity, shield: SuperiorShieldCap, stack: ItemStack)

	/**
	 * Called when the shield is unequipped
	 */
	fun onUnequipShield(entity: LivingEntity, shield: SuperiorShieldCap)

	/**
	 * Calculates the actual stats of the shield
	 */
	fun calculateShieldAttributes(stack: ItemStack): SuperiorShieldAttributes
}