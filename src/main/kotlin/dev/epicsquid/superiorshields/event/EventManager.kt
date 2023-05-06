package dev.epicsquid.superiorshields.event

import dev.epicsquid.superiorshields.SuperiorShields
import dev.epicsquid.superiorshields.SuperiorShields.SUPERIOR_SHIELD_CURIO
import dev.epicsquid.superiorshields.capability.SuperiorShieldCap
import dev.epicsquid.superiorshields.capability.SuperiorShieldCapProvider
import dev.epicsquid.superiorshields.network.NetworkHandler
import dev.epicsquid.superiorshields.network.SuperiorShieldUpdatePacket
import dev.epicsquid.superiorshields.registry.CapabilityRegistry.SUPERIOR_SHIELD_CAP_ID
import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
import dev.epicsquid.superiorshields.shield.SuperiorShield
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.Entity
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.event.entity.EntityJoinLevelEvent
import net.minecraftforge.event.entity.living.LivingHurtEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.network.PacketDistributor
import top.theillusivec4.curios.api.CuriosApi

@EventBusSubscriber(modid = SuperiorShields.MODID, bus = EventBusSubscriber.Bus.FORGE)
object EventManager {

	@SubscribeEvent
	fun onRegisterCapabilities(event: RegisterCapabilitiesEvent) {
		event.register(SuperiorShieldCap::class.java)
	}

	@SubscribeEvent
	fun onAttachCapabilities(event: AttachCapabilitiesEvent<Entity>) {
		event.addCapability(SUPERIOR_SHIELD_CAP_ID, SuperiorShieldCapProvider())
	}

	@SubscribeEvent
	fun onEntityJoinLevel(event: EntityJoinLevelEvent) {
		val player = event.entity as? ServerPlayer ?: return
		NetworkHandler.CHANNEL.send(
			PacketDistributor.PLAYER.with { player },
			SuperiorShieldUpdatePacket(player.shield.hp)
		)
	}

	@SubscribeEvent
	fun onLivingHurtEvent(event: LivingHurtEvent) {
		if (event.isCanceled) return

		val attacker = event.source.entity
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
}