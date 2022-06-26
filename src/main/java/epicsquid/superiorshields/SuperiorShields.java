package epicsquid.superiorshields;

import epicsquid.superiorshields.item.ModItems;
import epicsquid.superiorshields.proxy.ModSetup;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("superiorshields")
public class SuperiorShields {
  public static final String MODID = "superiorshields";
  public static final String SHIELD_CURIO = "superior_shield";

  public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab("superior_shields") {
    @Override
    public ItemStack makeIcon() {
      return new ItemStack(ModItems.IRON_SHIELD);
    }
  };

  public static ModSetup setup = new ModSetup();

  public SuperiorShields() {
    FMLJavaModLoadingContext.get().getModEventBus().addListener(setup::setup);
    FMLJavaModLoadingContext.get().getModEventBus().addListener(setup::enqueue);
    //		ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));
  }
}
