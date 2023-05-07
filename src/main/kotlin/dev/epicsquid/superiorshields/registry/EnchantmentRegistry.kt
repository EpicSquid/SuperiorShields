package dev.epicsquid.superiorshields.registry

import dev.epicsquid.superiorshields.SuperiorShields
import dev.epicsquid.superiorshields.effects.DefaultEffectHandler
import dev.epicsquid.superiorshields.effects.EffectHandler
import dev.epicsquid.superiorshields.effects.ScalableEffectHandler
import dev.epicsquid.superiorshields.effects.SuperiorShieldEffects
import dev.epicsquid.superiorshields.enchantment.*
import dev.epicsquid.superiorshields.shield.SuperiorShield
import dev.epicsquid.superiorshields.utils.registryEntry
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory

object EnchantmentRegistry {

	private val type = EnchantmentCategory.create("superior_shield") { it is SuperiorShield }

	private val registrate = SuperiorShields.registrate

	val capacity: CapacityEnchantment by registryEntry {
		registrate.enchantment<CapacityEnchantment>("capacity", type, factory(::CapacityEnchantment))
			.rarity(Enchantment.Rarity.COMMON)
			.lang("Shield Capacity")
			.register()
	}

	val raging: RagingEnchantment by registryEntry {
		registrate.enchantment<RagingEnchantment>("raging", type, factory(::RagingEnchantment))
			.rarity(Enchantment.Rarity.RARE)
			.lang("Raging")
			.register()
	}

	val amplify: AmplifyEnchantment by registryEntry {
		registrate.enchantment<AmplifyEnchantment>("amplify", type, factory(::AmplifyEnchantment))
			.rarity(Enchantment.Rarity.RARE)
			.lang("Amplify")
			.register()
	}

	val curing: CuringEnchantment by registryEntry {
		registrate.enchantment<CuringEnchantment>(
			"curing", type, factory(
				::CuringEnchantment,
				ScalableEffectHandler(
					empty = { trigger, _ -> SuperiorShieldEffects.CURING(trigger.shieldHolder) },
				)
			)
		).rarity(Enchantment.Rarity.RARE)
			.lang("Curing")
			.register()
	}

	val quickened: QuickenedEnchantment by registryEntry {
		registrate.enchantment<QuickenedEnchantment>("quickened", type, factory(::QuickenedEnchantment))
			.rarity(Enchantment.Rarity.COMMON)
			.lang("Quickened")
			.register()
	}

	val fireNova: NovaEnchantment by registryEntry {
		registrate.enchantment<NovaEnchantment>(
			"fire_nova", type, factory(
				::NovaEnchantment,
				ScalableEffectHandler(
					empty = { trigger, scale -> SuperiorShieldEffects.FIRE_NOVA(trigger.shieldHolder, scale) }
				)
			)
		).rarity(Enchantment.Rarity.UNCOMMON)
			.lang("Fire Nova")
			.register()
	}

	val frostNova: NovaEnchantment by registryEntry {
		registrate.enchantment<NovaEnchantment>(
			"frost_nova", type, factory(
				::NovaEnchantment,
				ScalableEffectHandler(
					empty = { trigger, scale -> SuperiorShieldEffects.FROST_NOVA(trigger.shieldHolder, scale) }
				)
			)
		).rarity(Enchantment.Rarity.UNCOMMON)
			.lang("Frost Nova")
			.register()
	}

	val shulkingNova: NovaEnchantment by registryEntry {
		registrate.enchantment<NovaEnchantment>(
			"shulking_nova", type, factory(
				::NovaEnchantment,
				ScalableEffectHandler(
					empty = { trigger, scale -> SuperiorShieldEffects.SHULKING_NOVA(trigger.shieldHolder, scale) }
				)
			)
		).rarity(Enchantment.Rarity.UNCOMMON)
			.lang("Shulking Nova")
			.register()
	}

	val poisonSpikes: SpikeEnchantment by registryEntry {
		registrate.enchantment<SpikeEnchantment>(
			"poison_spikes", type, factory(
				::SpikeEnchantment,
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
		).rarity(Enchantment.Rarity.UNCOMMON)
			.lang("Poison Spikes")
			.register()
	}

	val witherSpikes: SpikeEnchantment by registryEntry {
		registrate.enchantment<SpikeEnchantment>(
			"wither_spikes", type, factory(
				::SpikeEnchantment,
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
		).rarity(Enchantment.Rarity.UNCOMMON)
			.lang("Wither Spikes")
			.register()
	}

	private inline fun <reified T : SuperiorShieldEnchantment> factory(
		crossinline enchantmentConstructor: (Enchantment.Rarity, EnchantmentCategory, EffectHandler) -> T,
		effectHandler: EffectHandler = DefaultEffectHandler.NONE
	): (Enchantment.Rarity, EnchantmentCategory, Array<out EquipmentSlot>) -> T {
		return { rarity, type, _ -> enchantmentConstructor(rarity, type, effectHandler) }
	}
}