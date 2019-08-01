package epicsquid.superiorshields;

import epicsquid.superiorshields.init.ModItems;
import epicsquid.superiorshields.proxy.ModSetup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("superiorshields")
public class SuperiorShields {
  public static final String MODID = "superiorshields";

  public static final ItemGroup ITEM_GROUP = new ItemGroup("traverse") {
    @Override
    public ItemStack createIcon() {
      return new ItemStack(ModItems.IRON_SHIELD);
    }
  };

  public static ModSetup setup = new ModSetup();

  public SuperiorShields() {
    FMLJavaModLoadingContext.get().getModEventBus().addListener(setup::setup);

    //		ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));
  }
}
