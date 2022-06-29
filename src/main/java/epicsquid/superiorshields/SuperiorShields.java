package epicsquid.superiorshields;

import com.tterrag.registrate.Registrate;
import epicsquid.superiorshields.enchantment.ModEnchantments;
import epicsquid.superiorshields.item.ModItems;
import epicsquid.superiorshields.network.NetworkHandler;
import epicsquid.superiorshields.setup.ModSetup;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
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
		MinecraftForge.EVENT_BUS.register(this);

		ModItems.classload();
		ModEnchantments.classload();
	}

	public void setup(FMLCommonSetupEvent event) {
		NetworkHandler.register();
	}

	public void sendImc(InterModEnqueueEvent event) {
		ModSetup.sendImc();
	}

	public static Registrate registrate() {
		return REGISTRATE.get();
	}
}
