package epicsquid.superiorshields.proxy;

import epicsquid.superiorshields.capability.shield.SuperiorShieldsCapabilityManager;
import epicsquid.superiorshields.init.ModItems;
import epicsquid.superiorshields.network.PacketHandler;
import epicsquid.superiorshields.shield.perk.PerkEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
  public void preInit(FMLPreInitializationEvent event) {
    SuperiorShieldsCapabilityManager.preInit();
    PacketHandler.registerMessages();
    ModItems.registerOredict();

    if (Loader.isModLoaded("astralsorcery")) {
      MinecraftForge.EVENT_BUS.register(new PerkEventHandler());
    }
  }

  public void init(FMLInitializationEvent event) {
  }

  public void postInit(FMLPostInitializationEvent event) {
  }
}
