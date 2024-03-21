package dev.epicsquid.superiorshields.enchantment

import dev.epicsquid.superiorshields.capability.SuperiorShieldCap
import dev.epicsquid.superiorshields.config.Config
import dev.epicsquid.superiorshields.effects.EffectHandler
import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
import dev.epicsquid.superiorshields.shield.SuperiorShield
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory

open class AmplifyEnchantment(
	rarity: Rarity,
	category: EnchantmentCategory,
	effectHandler: EffectHandler
) : DamageBoostEnchantment(rarity, category, effectHandler) {

	override fun getMaxLevel(): Int = 1

	override fun getMinCost(level: Int): Int = 15

	override fun getMaxCost(level: Int): Int = super.getMinCost(level) + 50

	override fun boostDamage(damage: Float): Float =
		damage * Config.SHIELDS_CONFIG.amplifyDamageMultiplier.get().toFloat()

	override fun checkCompatibility(other: Enchantment): Boolean = other !is AmplifyEnchantment

	override fun shouldBoostDamage(entity: LivingEntity): Boolean {
		val shield = entity.shield
		val capacity = entity.shield.capacity

		return if (shield.hp >= capacity && capacity > 0) {
			drainShield(shield)
			true
		} else {
			false
		}
	}

	protected open fun drainShield(shield: SuperiorShieldCap) {
		shield.hp -= Config.SHIELDS_CONFIG.amplifyShieldDrain.get().toInt()
		shield.ticksWithoutDamage = 0
	}
}