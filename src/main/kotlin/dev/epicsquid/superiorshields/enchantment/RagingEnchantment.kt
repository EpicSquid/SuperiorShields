package dev.epicsquid.superiorshields.enchantment

import dev.epicsquid.superiorshields.config.Config
import dev.epicsquid.superiorshields.effects.EffectHandler
import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.enchantment.EnchantmentCategory

class RagingEnchantment(
	rarity: Rarity,
	category: EnchantmentCategory,
	effectHandler: EffectHandler
) : DamageBoostEnchantment(rarity, category, effectHandler) {

	override fun getMaxLevel(): Int = 1

	override fun getMinCost(level: Int): Int = 1

	override fun getMaxCost(level: Int): Int = super.getMinCost(level) + 40

	override fun boostDamage(damage: Float): Float =
		damage + Config.SHIELDS_CONFIG.ragingDamageAdded.get().toFloat()

	override fun shouldBoostDamage(entity: LivingEntity): Boolean =
		entity.shield.hp <= 0


}