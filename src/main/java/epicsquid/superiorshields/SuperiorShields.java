package epicsquid.superiorshields;

import com.tterrag.registrate.Registrate;
import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.enchantment.ModEnchantments;
import epicsquid.superiorshields.item.ModItems;
import epicsquid.superiorshields.lang.ModLang;
import epicsquid.superiorshields.network.NetworkHandler;
import epicsquid.superiorshields.setup.ModSetup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nonnull;

@Mod(SuperiorShields.MODID)
public class SuperiorShields {

	public static final String MODID = "superiorshields";

	private static final Lazy<Registrate> REGISTRATE = Lazy.of(() -> Registrate.create(MODID));
	public static final String SHIELD_CURIO = "superior_shield";

	public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab("superior_shields") {
		@Override
		@Nonnull
		public ItemStack makeIcon() {
			return new ItemStack(ModItems.IRON_SHIELD.get());
		}
	};

	public SuperiorShields() {
		//		ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::sendImc);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerCaps);
		MinecraftForge.EVENT_BUS.register(this);

		ModItems.classload();
		ModEnchantments.classload();
		ModLang.classload();
	}

	public void setup(FMLCommonSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(new EventManager());
		NetworkHandler.register();
	}

	@SubscribeEvent
	public void registerCaps(final RegisterCapabilitiesEvent event) {
		event.register(IShieldCapability.class);
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
			MinecraftForge.EVENT_BUS.register(new ClientEventManager());
		}
	}
}
