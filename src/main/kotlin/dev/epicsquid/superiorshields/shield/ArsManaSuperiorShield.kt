package dev.epicsquid.superiorshields.shield

import com.hollingsworth.arsnouveau.common.spell.casters.ReactiveCaster
import com.hollingsworth.arsnouveau.setup.registry.CapabilityRegistry
import com.hollingsworth.arsnouveau.setup.registry.EnchantmentRegistry
import dev.epicsquid.superiorshields.capability.SuperiorShieldCap
import dev.epicsquid.superiorshields.config.SuperiorShieldStats
import dev.epicsquid.superiorshields.effects.EffectTrigger
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand.MAIN_HAND
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack

class ArsManaSuperiorShield(
	name: String,
	config: SuperiorShieldStats
) : DurabilitySuperiorShield(name, config) {

	private fun repairWithMana(stack: ItemStack, entity: LivingEntity) {
		CapabilityRegistry.getMana(entity).ifPresent { mana ->
			if (mana.currentMana > 20.0 && stack.isDamaged) {
				mana.removeMana(20.0)
				stack.damageValue--
			}
		}
	}

	override fun shieldTick(entity: LivingEntity, shield: SuperiorShieldCap, stack: ItemStack) {
		super.shieldTick(entity, shield, stack)
		repairWithMana(stack, entity)
	}
}

// This is here so that our imports are all in the same file
object ReactiveHelper {
	fun triggerReactive(effectTrigger: EffectTrigger) {
		if (effectTrigger is EffectTrigger.Empty
			&& effectTrigger.shieldStack.getEnchantmentLevel(EnchantmentRegistry.REACTIVE_ENCHANTMENT.get()) * .25 >= Math.random()
		) {
			val caster = ReactiveCaster(effectTrigger.shieldStack)
			if (caster.spell.isValid) {
				caster.castSpell(effectTrigger.shieldHolder.level(), effectTrigger.shieldHolder, MAIN_HAND, Component.empty())
			}
		}
	}
}