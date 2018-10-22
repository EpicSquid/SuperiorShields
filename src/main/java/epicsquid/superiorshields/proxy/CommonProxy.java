package epicsquid.superiorshields.proxy;

import epicsquid.superiorshields.capability.SuperiorShieldsCapabilityManager;
import epicsquid.superiorshields.init.ModItems;
import epicsquid.superiorshields.network.PacketHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
  public void preInit(FMLPreInitializationEvent event) {
    SuperiorShieldsCapabilityManager.preInit();
    PacketHandler.registerMessages();
    ModItems.registerOredict();
  }

  public void init(FMLInitializationEvent event) {
  }

  public void postInit(FMLPostInitializationEvent event) {
  }
}
