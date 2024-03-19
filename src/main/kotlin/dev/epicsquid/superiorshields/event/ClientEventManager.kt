package dev.epicsquid.superiorshields.event

import dev.epicsquid.superiorshields.gui.SuperiorShieldOverlay
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay
import net.minecraftforge.eventbus.api.SubscribeEvent

object ClientEventManager {

	@SubscribeEvent
	fun onRegisterGuiOverlays(event: RegisterGuiOverlaysEvent) {
		event.registerAbove(VanillaGuiOverlay.PLAYER_HEALTH.id(), "superior_shield_overlay", SuperiorShieldOverlay())
	}
}