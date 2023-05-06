package dev.epicsquid.superiorshields.event

import com.mojang.blaze3d.systems.RenderSystem
import dev.epicsquid.superiorshields.SuperiorShields
import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.player.Player
import net.minecraftforge.api.distmarker.Dist.CLIENT
import net.minecraftforge.client.event.RenderPlayerEvent
import net.minecraftforge.event.TickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber

@EventBusSubscriber(modid = SuperiorShields.MODID, value = [CLIENT], bus = EventBusSubscriber.Bus.FORGE)
object ClientEventManager {

	private val hurtTime: MutableMap<Player, Int> = mutableMapOf()

	@SubscribeEvent
	fun onRenderPlayerPre(event: RenderPlayerEvent.Pre) {
		val shield = event.entity.shield
		if (shield.hp > 0) {
			// TODO add config to damage sources
			if (event.entity.lastDamageSource != DamageSource.STARVE && event.entity.lastDamageSource != DamageSource.DROWN) {
				if (event.entity.hurtTime > 0) {
					hurtTime[event.entity] = event.entity.hurtTime
					event.entity.hurtTime = 0
				}
				if (event.entity in hurtTime) {
					RenderSystem.clearColor(204 / 256f, 255 / 255f, 251 / 255f, 1f)
				}
			}
		}
	}

	@SubscribeEvent
	fun onPlayerTick(event: TickEvent.PlayerTickEvent) {
		if (event.phase == TickEvent.Phase.START && event.player in hurtTime) {
			hurtTime[event.player] = hurtTime[event.player]!! - 1
			if (hurtTime[event.player]!! <= 0) {
				hurtTime.remove(event.player)
			}
		}
	}
}