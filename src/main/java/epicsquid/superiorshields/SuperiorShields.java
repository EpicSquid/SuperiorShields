package epicsquid.superiorshields;

import epicsquid.superiorshields.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = SuperiorShields.MODID, version = SuperiorShields.VERSION, name = SuperiorShields.NAME)
public class SuperiorShields {
  public static final String MODID = "superiorshields";
  public static final String DOMAIN = "superiorshields";
  public static final String NAME = "Superior Shields";
  public static final String VERSION = "@VERSION@";

  public static ModContainer CONTAINER = null;

  @SidedProxy(clientSide = "epicsquid.superiorshields.proxy.ClientProxy", serverSide = "epicsquid.superiorshields.proxy.CommonProxy")
  public static CommonProxy proxy;

  @Instance(MODID) public static SuperiorShields instance;

  public static CreativeTabs tab = new CreativeTabs("superiorshields") {
    @Override
    public String getTabLabel() {
      return "superiorshields";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
      return ItemStack.EMPTY;
    }
  };

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    CONTAINER = Loader.instance().activeModContainer();
    MinecraftForge.EVENT_BUS.register(new EventManager());
    MinecraftForge.EVENT_BUS.register(new RegistryManager());
    proxy.preInit(event);
  }

  public static SuperiorShields getInstance() {
    return instance;
  }

  @EventHandler
  public void init(FMLInitializationEvent event) {
    proxy.init(event);
  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {
    proxy.postInit(event);
  }
}
