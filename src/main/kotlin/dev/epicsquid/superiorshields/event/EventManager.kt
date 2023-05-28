package dev.epicsquid.superiorshields.event

import dev.epicsquid.superiorshields.SuperiorShields.Companion.SUPERIOR_SHIELD_CURIO
import dev.epicsquid.superiorshields.capability.SuperiorShieldCap
import dev.epicsquid.superiorshields.capability.SuperiorShieldCapProvider
import dev.epicsquid.superiorshields.enchantment.DamageBoostEnchantment
import dev.epicsquid.superiorshields.network.NetworkHandler
import dev.epicsquid.superiorshields.network.SuperiorShieldUpdatePacket
import dev.epicsquid.superiorshields.registry.CapabilityRegistry
import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
import dev.epicsquid.superiorshields.shield.SuperiorShield
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.event.entity.EntityJoinLevelEvent
import net.minecraftforge.event.entity.living.LivingDamageEvent
import net.minecraftforge.event.entity.living.LivingHurtEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.network.PacketDistributor
import top.theillusivec4.curios.api.CuriosApi
import javax.annotation.Nonnull

object EventManager {

	@SubscribeEvent
	fun onEntityJoinLevel(event: EntityJoinLevelEvent) {
		val player = event.entity as? ServerPlayer ?: return
		val shield = player.shield
		NetworkHandler.CHANNEL.send(
			PacketDistributor.PLAYER.with { player },
			SuperiorShieldUpdatePacket(shield.hp, shield.capacity)
		)
	}

	@SubscribeEvent
	fun onLivingDamageEvent(event: LivingDamageEvent) {
		if (event.isCanceled) return

		val target = event.entity

		// Handle the shield absorbing damage
		// Check what the source of the damage was
		// TODO add the option to blacklist more/less damage sources
		if (event.source != DamageSource.STARVE && event.source != DamageSource.DROWN) {
			CuriosApi.getCuriosHelper().getCuriosHandler(target).ifPresent { handler ->
				handler.getStacksHandler(SUPERIOR_SHIELD_CURIO).ifPresent { stackHandler ->
					val stack = stackHandler.stacks.getStackInSlot(0)
					if (!stack.isEmpty) {
						(stack.item as? SuperiorShield)?.let {
							event.amount = it.applyShield(
								entity = target,
								stack = stack,
								damage = event.amount,
								source = event.source
							)
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	fun onLivingHurtEvent(event: LivingHurtEvent) {
		if (event.isCanceled) return

		val attacker = event.source.entity

		if (attacker is LivingEntity) {
			// Handle the shield dealing damage
			CuriosApi.getCuriosHelper().getCuriosHandler(attacker).ifPresent { handler ->
				handler.getStacksHandler(SUPERIOR_SHIELD_CURIO).ifPresent { stackHandler ->
					val stack = stackHandler.stacks.getStackInSlot(0)
					if (!stack.isEmpty) {
						EnchantmentHelper.getEnchantments(stack).forEach() { (enchantment, _) ->
							if (enchantment is DamageBoostEnchantment && enchantment.shouldBoostDamage(attacker)) {
								event.amount = enchantment.boostDamage(event.amount)
							}
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	fun onAttachCapabilities(@Nonnull event: AttachCapabilitiesEvent<Entity>) {
		if (event.`object` !is LivingEntity) return
		event.addCapability(CapabilityRegistry.SUPERIOR_SHIELD_CAP_ID, SuperiorShieldCapProvider())
	}

	@SubscribeEvent
	fun onRegisterCapabilities(event: RegisterCapabilitiesEvent) {
		event.register(SuperiorShieldCap::class.java)
	}
}