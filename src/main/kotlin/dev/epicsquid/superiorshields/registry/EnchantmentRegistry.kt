package dev.epicsquid.superiorshields.registry

import dev.epicsquid.superiorshields.SuperiorShields
import dev.epicsquid.superiorshields.effects.DefaultEffectHandler
import dev.epicsquid.superiorshields.effects.ScalableEffectHandler
import dev.epicsquid.superiorshields.effects.SuperiorShieldEffects
import dev.epicsquid.superiorshields.enchantment.*
import dev.epicsquid.superiorshields.shield.SuperiorShield
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.forge.registerObject

object EnchantmentRegistry {

	val REGISTRY = DeferredRegister.create(Registries.ENCHANTMENT, SuperiorShields.MODID)

	private val type = EnchantmentCategory.create("superior_shield") { it is SuperiorShield }

	val capacity by REGISTRY.registerObject("capacity") {
		CapacityEnchantment(Enchantment.Rarity.COMMON, type, DefaultEffectHandler.NONE)
	}

	val raging by REGISTRY.registerObject("raging") {
		RagingEnchantment(Enchantment.Rarity.RARE, type, DefaultEffectHandler.NONE)
	}

	val amplify by REGISTRY.registerObject("amplify") {
		AmplifyEnchantment(Enchantment.Rarity.RARE, type, DefaultEffectHandler.NONE)
	}

	val curing by REGISTRY.registerObject("curing") {
		CuringEnchantment(
			Enchantment.Rarity.RARE,
			type,
			ScalableEffectHandler(empty = { trigger, _ -> SuperiorShieldEffects.CURING(trigger.shieldHolder) })
		)
	}

	val quickened by REGISTRY.registerObject("quickened") {
		QuickenedEnchantment(Enchantment.Rarity.RARE, type, DefaultEffectHandler.NONE)
	}

	val fireNova by REGISTRY.registerObject("fire_nova") {
		NovaEnchantment(
			Enchantment.Rarity.UNCOMMON,
			type,
			ScalableEffectHandler(empty = { trigger, scale -> SuperiorShieldEffects.FIRE_NOVA(trigger.shieldHolder, scale) })
		)
	}

	val frostNova by REGISTRY.registerObject("frost_nova") {
		NovaEnchantment(
			Enchantment.Rarity.UNCOMMON,
			type,
			ScalableEffectHandler(empty = { trigger, scale -> SuperiorShieldEffects.FROST_NOVA(trigger.shieldHolder, scale) })
		)
	}

	val shulkingNova by REGISTRY.registerObject("shulking_nova") {
		NovaEnchantment(
			Enchantment.Rarity.UNCOMMON,
			type,
			ScalableEffectHandler(empty = { trigger, scale ->
				SuperiorShieldEffects.SHULKING_NOVA(
					trigger.shieldHolder,
					scale
				)
			})
		)
	}

	val poisonSpikes by REGISTRY.registerObject("poison_spikes") {
		SpikeEnchantment(
			Enchantment.Rarity.UNCOMMON,
			type,
			ScalableEffectHandler(
				damage = { trigger, scale ->
					SuperiorShieldEffects.POISON_SPIKES(
						trigger.shieldHolder,
						scale,
						trigger.damageSource
					)
				}
			)
		)
	}

	val witherSpikes by REGISTRY.registerObject("wither_spikes") {
		SpikeEnchantment(
			Enchantment.Rarity.UNCOMMON,
			type,
			ScalableEffectHandler(
				damage = { trigger, scale ->
					SuperiorShieldEffects.WITHER_SPIKES(
						trigger.shieldHolder,
						scale,
						trigger.damageSource
					)
				}
			)
		)
	}

	val flameOfTheFirehawk by REGISTRY.registerObject("flame_of_the_firehawk") {
		ContinualNovaEnchantment(
			Enchantment.Rarity.RARE,
			type,
			ScalableEffectHandler(continualEmpty = { trigger, scale ->
				if (trigger.ticksPassed % 40 == 0) {
					SuperiorShieldEffects.FLAME_OF_THE_FIREHAWK(
						trigger.shieldHolder,
						scale * 3
					)
				}
			})
		)
	}
}