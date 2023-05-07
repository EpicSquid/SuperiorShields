package dev.epicsquid.superiorshields

import com.tterrag.registrate.Registrate
import dev.epicsquid.superiorshields.config.Config
import dev.epicsquid.superiorshields.event.ClientEventManager
import dev.epicsquid.superiorshields.event.EventManager
import dev.epicsquid.superiorshields.gui.SuperiorShieldOverlay
import dev.epicsquid.superiorshields.network.NetworkHandler
import dev.epicsquid.superiorshields.registry.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.inventory.InventoryMenu
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraftforge.api.distmarker.Dist.CLIENT
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent
import net.minecraftforge.client.event.TextureStitchEvent
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD
import net.minecraftforge.fml.config.ModConfig
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext

@Mod(SuperiorShields.MODID)
class SuperiorShields {
	companion object {
		const val MODID = "superiorshields"
		const val SUPERIOR_SHIELD_CURIO = "superior_shield"

		val tab = object : CreativeModeTab(MODID) {
			override fun makeIcon() = ItemStack(ItemRegistry.ironShield)
		}

		val registrate: Registrate by lazy {
			Registrate.create(MODID).creativeModeTab { tab }
		}
	}

	init {
		ModLoadingContext.get()
			.registerConfig(ModConfig.Type.SERVER, Config.SHIELDS_CONFIG_SPEC, "superior-shields-server.toml")
		val modEventBus = FMLJavaModLoadingContext.get().modEventBus

		modEventBus.addListener { _: FMLCommonSetupEvent -> NetworkHandler.register() }
		modEventBus.register(AttributeRegistry)
		AttributeRegistry.attributes.register(modEventBus)
		MinecraftForge.EVENT_BUS.register(EventManager)
		MinecraftForge.EVENT_BUS.register(CapabilityRegistry)

		EnchantmentRegistry.classload()
		ItemRegistry.classload()
		LangRegistry.classload()
	}
}

@EventBusSubscriber(modid = SuperiorShields.MODID, value = [CLIENT], bus = MOD)
object SuperiorShieldsClient {

	init {
		ClientEventManager.classload()
	}

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