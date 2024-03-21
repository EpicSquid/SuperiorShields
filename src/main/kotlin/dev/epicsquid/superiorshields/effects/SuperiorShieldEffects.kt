package dev.epicsquid.superiorshields.effects

import dev.epicsquid.superiorshields.config.Config
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.monster.Enemy
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items

object SuperiorShieldEffects {

	val CURING: (LivingEntity) -> Unit = {
		it.curePotionEffects(ItemStack(Items.MILK_BUCKET))
	}

	val FIRE_NOVA: (LivingEntity, Int) -> Unit = { entity, scale ->
		novaEffect(entity, scale) {
			it.setSecondsOnFire(Config.SHIELDS_CONFIG.novaEffectDuration.get())
		}
	}

	val FROST_NOVA: (LivingEntity, Int) -> Unit = { entity, scale ->
		novaEffect(entity, scale) {
			it.addEffect(MobEffectInstance(MobEffects.WEAKNESS, Config.SHIELDS_CONFIG.novaEffectDuration.get() * 20))
			it.addEffect(MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, Config.SHIELDS_CONFIG.novaEffectDuration.get() * 20))
		}
	}

	val SHULKING_NOVA: (LivingEntity, Int) -> Unit = { entity, scale ->
		novaEffect(entity, scale) {
			it.addEffect(MobEffectInstance(MobEffects.LEVITATION, Config.SHIELDS_CONFIG.novaEffectDuration.get() * 20))
		}
	}

	val POISON_SPIKES: (LivingEntity, Int, DamageSource) -> Unit = { entity, scale, damageSource ->
		spikeEffect(entity, damageSource) {
			it.addEffect(MobEffectInstance(MobEffects.POISON, Config.SHIELDS_CONFIG.spikeEffectDuration.get() * 20, scale))
		}
	}

	val WITHER_SPIKES: (LivingEntity, Int, DamageSource) -> Unit = { entity, scale, damageSource ->
		spikeEffect(entity, damageSource) {
			it.addEffect(MobEffectInstance(MobEffects.WITHER, Config.SHIELDS_CONFIG.spikeEffectDuration.get() * 20, scale))
		}
	}

	val FLAME_OF_THE_FIREHAWK: (LivingEntity, Int) -> Unit = { entity, scale ->
		novaEffect(entity, scale) {
			it.setSecondsOnFire(Config.SHIELDS_CONFIG.novaEffectDuration.get())
		}
	}

	private fun novaEffect(entity: LivingEntity, scale: Int, effect: (LivingEntity) -> Unit) {
		if (entity.level().isClientSide) return

		val range = scale.toDouble() * Config.SHIELDS_CONFIG.novaRange.get()

		entity.level().getEntitiesOfClass(LivingEntity::class.java, entity.boundingBox.inflate(range))
			.filter { it is Enemy }
			.forEach(effect)
	}

	private fun spikeEffect(
		entity: LivingEntity,
		damageSource: DamageSource,
		effect: (LivingEntity) -> Unit
	) {
		if (entity.level().isClientSide) return

		(damageSource.entity as? LivingEntity)?.let(effect)
	}
}