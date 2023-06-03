package dev.epicsquid.superiorshields.effects

import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack

sealed interface EffectTrigger {

	val shieldStack: ItemStack

	data class Damage(
		val shieldHolder: LivingEntity,
		val damageSource: DamageSource,
		val damage: Float,
		override val shieldStack: ItemStack,
	) : EffectTrigger

	data class Full(
		val shieldHolder: LivingEntity,
		override val shieldStack: ItemStack,
	) : EffectTrigger

	data class Empty(
		val shieldHolder: LivingEntity,
		override val shieldStack: ItemStack,
	) : EffectTrigger

	data class Recharge(
		val shieldHolder: LivingEntity,
		override val shieldStack: ItemStack,
	) : EffectTrigger

	data class Filled(
		val shieldHolder: LivingEntity,
		override val shieldStack: ItemStack,
	) : EffectTrigger
}

