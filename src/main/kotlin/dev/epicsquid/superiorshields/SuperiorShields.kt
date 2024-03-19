package dev.epicsquid.superiorshields

import dev.epicsquid.superiorshields.config.Config
import dev.epicsquid.superiorshields.data.SuperiorShieldsItemModels
import dev.epicsquid.superiorshields.data.SuperiorShieldsItemTags
import dev.epicsquid.superiorshields.data.SuperiorShieldsLang
import dev.epicsquid.superiorshields.data.SuperiorShieldsRecipes
import dev.epicsquid.superiorshields.event.ClientEventManager
import dev.epicsquid.superiorshields.event.EventManager
import dev.epicsquid.superiorshields.network.NetworkHandler
import dev.epicsquid.superiorshields.registry.CreativeTabsRegistry
import dev.epicsquid.superiorshields.registry.EnchantmentRegistry
import dev.epicsquid.superiorshields.registry.ItemRegistry
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.data.ForgeBlockTagsProvider
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.eventbus.api.EventPriority.LOWEST
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.config.ModConfig
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist

@Mod(SuperiorShields.MODID)
object SuperiorShields {
	const val MODID = "superiorshields"

	init {
		ModLoadingContext.get()
			.registerConfig(ModConfig.Type.SERVER, Config.SHIELDS_CONFIG_SPEC, "superior-shields-server.toml")
		val modEventBus = MOD_BUS

		MinecraftForge.EVENT_BUS.register(EventManager)
		modEventBus.addListener { _: FMLCommonSetupEvent -> NetworkHandler.register() }
		runForDist(
			clientTarget = { modEventBus.register(ClientEventManager) },
			serverTarget = {}
		)

		EnchantmentRegistry.REGISTRY.register(modEventBus)
		ItemRegistry.REGISTRY.register(modEventBus)
		CreativeTabsRegistry.REGISTRY.register(modEventBus)

		modEventBus.addListener(LOWEST, this::onGatherData)
	}

	private fun onGatherData(event: GatherDataEvent) {
		val generator = event.generator
		val output = generator.packOutput
		generator.addProvider(event.includeClient(), SuperiorShieldsLang(output, "en_us"))
		generator.addProvider(event.includeClient(), SuperiorShieldsItemModels(output, event.existingFileHelper))

		val blockTagsProvider = ForgeBlockTagsProvider(output, event.lookupProvider, event.existingFileHelper)
		generator.addProvider(
			event.includeServer(),
			SuperiorShieldsItemTags(output, event.lookupProvider, blockTagsProvider, event.existingFileHelper)
		)
		generator.addProvider(event.includeServer(), SuperiorShieldsRecipes(output))
	}
}