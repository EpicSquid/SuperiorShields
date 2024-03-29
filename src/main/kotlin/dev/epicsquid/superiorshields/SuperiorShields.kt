package dev.epicsquid.superiorshields

import com.tterrag.registrate.Registrate
import dev.epicsquid.superiorshields.config.Config
import dev.epicsquid.superiorshields.data.SuperiorShieldsTags
import dev.epicsquid.superiorshields.event.ClientEventManager
import dev.epicsquid.superiorshields.event.EventManager
import dev.epicsquid.superiorshields.network.NetworkHandler
import dev.epicsquid.superiorshields.registry.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.data.ForgeBlockTagsProvider
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.eventbus.api.EventPriority.LOWEST
import net.minecraftforge.fml.InterModComms
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.config.ModConfig
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import thedarkcolour.kotlinforforge.forge.runForDist
import top.theillusivec4.curios.api.CuriosApi
import top.theillusivec4.curios.api.SlotTypeMessage
import top.theillusivec4.curios.api.SlotTypeMessage.Builder

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

		MinecraftForge.EVENT_BUS.register(EventManager)
		modEventBus.addListener { _: FMLCommonSetupEvent -> NetworkHandler.register() }
		modEventBus.addListener { _: InterModEnqueueEvent -> onInterModEnqueue() }
		runForDist(
			clientTarget = { modEventBus.register(ClientEventManager) },
			serverTarget = {}
		)

		EnchantmentRegistry.classload()
		ItemRegistry.classload()
		LangRegistry.classload()

		FMLJavaModLoadingContext.get().modEventBus.addListener(LOWEST, this::onGatherData)
	}

	private fun onInterModEnqueue() {
		InterModComms.sendTo(
			CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE
		) {
			Builder(SUPERIOR_SHIELD_CURIO).apply {
				priority(220)
				icon(ResourceLocation(MODID, "item/empty_shield_slot"))
			}.build()
		}
	}

	private fun onGatherData(event: GatherDataEvent) {
		val generator = event.generator
		val blockTagsProvider = ForgeBlockTagsProvider(generator, event.existingFileHelper)
		generator.addProvider(
			event.includeServer(),
			SuperiorShieldsTags(generator, blockTagsProvider, event.existingFileHelper)
		)
	}
}