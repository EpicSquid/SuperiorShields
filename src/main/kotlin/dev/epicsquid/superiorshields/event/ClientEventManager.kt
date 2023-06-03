package dev.epicsquid.superiorshields.event

import dev.epicsquid.superiorshields.SuperiorShields
import dev.epicsquid.superiorshields.gui.SuperiorShieldOverlay
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.inventory.InventoryMenu
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent
import net.minecraftforge.client.event.TextureStitchEvent
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay
import net.minecraftforge.eventbus.api.SubscribeEvent

object ClientEventManager {

	@SubscribeEvent
	fun onStitchTexturesPre(event: TextureStitchEvent.Pre) {
		if (event.atlas.location() == InventoryMenu.BLOCK_ATLAS) {
			event.addSprite(ResourceLocation(SuperiorShields.MODID, "item/empty_shield_slot"))
		}
	}

	@SubscribeEvent
	fun onRegisterGuiOverlays(event: RegisterGuiOverlaysEvent) {
		event.registerAbove(VanillaGuiOverlay.PLAYER_HEALTH.id(), "superior_shield_overlay", SuperiorShieldOverlay())
	}
}