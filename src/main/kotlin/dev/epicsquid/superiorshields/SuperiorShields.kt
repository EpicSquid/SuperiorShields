package dev.epicsquid.superiorshields

import com.tterrag.registrate.Registrate
import dev.epicsquid.superiorshields.gui.SuperiorShieldOverlay
import dev.epicsquid.superiorshields.network.NetworkHandler
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.inventory.InventoryMenu
import net.minecraft.world.item.CreativeModeTab
import net.minecraftforge.api.distmarker.Dist.CLIENT
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent
import net.minecraftforge.client.event.TextureStitchEvent
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import org.apache.logging.log4j.LogManager
import thedarkcolour.kotlinforforge.forge.MOD_CONTEXT

@Mod(SuperiorShields.MODID)
object SuperiorShields {
	const val MODID = "superiorshields"
	const val SUPERIOR_SHIELD_CURIO = "superior_shield"

	val logger = LogManager.getLogger(MODID)

	val registrate by lazy {
		Registrate.create(MODID)
	}

	val tab = object : CreativeModeTab(MODID) {
		override fun makeIcon() = TODO()
	}

	init {
		MOD_CONTEXT.getKEventBus().addListener(::setup)
	}

	private fun setup(event: FMLCommonSetupEvent) {
		NetworkHandler.register()
	}
}

@EventBusSubscriber(modid = SuperiorShields.MODID, value = [CLIENT], bus = MOD)
object SuperiorShieldsClient {

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