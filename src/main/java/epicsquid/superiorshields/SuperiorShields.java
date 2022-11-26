package epicsquid.superiorshields;

import com.tterrag.registrate.Registrate;
import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.config.Config;
import epicsquid.superiorshields.enchantment.ModEnchantments;
import epicsquid.superiorshields.gui.GuiShieldOverlay;
import epicsquid.superiorshields.item.ModItems;
import epicsquid.superiorshields.lang.ModLang;
import epicsquid.superiorshields.network.NetworkHandler;
import epicsquid.superiorshields.setup.ModSetup;
import epicsquid.superiorshields.setup.compat.ArsCompat;
import epicsquid.superiorshields.setup.compat.BotaniaCompat;
import epicsquid.superiorshields.setup.compat.MalumCompat;
import epicsquid.superiorshields.setup.compat.ThermalCompat;
import epicsquid.superiorshields.tags.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import javax.annotation.Nonnull;

@Mod(SuperiorShields.MODID)
public class SuperiorShields {

	public static final String MODID = "superiorshields";

	private static final Lazy<Registrate> REGISTRATE = Lazy.of(() -> Registrate.create(MODID));
	public static final String SHIELD_CURIO = "superior_shield";

	public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(SuperiorShields.MODID) {
		@Override
		@Nonnull
		public ItemStack makeIcon() {
			return new ItemStack(ModItems.IRON_SHIELD.get());
		}
	};

	public SuperiorShields() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SHIELD_SPEC, "superior-shields-common.toml");
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::sendImc);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerCaps);
		MinecraftForge.EVENT_BUS.register(this);

		BotaniaCompat.init();
		MalumCompat.init();
		ThermalCompat.init();
		ArsCompat.init();

		ModItems.classload();
		ModEnchantments.classload();
		ModLang.classload();

		FMLJavaModLoadingContext.get().getModEventBus().addListener(EventPriority.LOWEST, this::gatherData);
	}

	public void setup(FMLCommonSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(new EventManager());
		NetworkHandler.register();
	}

	@SubscribeEvent
	public void registerCaps(final RegisterCapabilitiesEvent event) {
		event.register(IShieldCapability.class);
	}

	public void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		if (event.includeServer()) {
			ForgeBlockTagsProvider b = new ForgeBlockTagsProvider(generator, event.getExistingFileHelper());
			generator.addProvider(new ModTags(generator, b, event.getExistingFileHelper()));
		}
	}

	public void sendImc(InterModEnqueueEvent event) {
		ModSetup.sendImc();
	}

	public static Registrate registrate() {
		return REGISTRATE.get();
	}

	@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class ClientProxy {
		@SubscribeEvent
		public static void stitchTextures(TextureStitchEvent.Pre event) {
			if (event.getAtlas().location() == InventoryMenu.BLOCK_ATLAS) {
				event.addSprite(new ResourceLocation(SuperiorShields.MODID, "item/empty_shield_slot"));
			}
		}

		@SubscribeEvent
		public static void setupClient(FMLClientSetupEvent event) {
//			MinecraftForge.EVENT_BUS.register(new ClientEventManager());
			GuiShieldOverlay.init();
		}
	}
}
