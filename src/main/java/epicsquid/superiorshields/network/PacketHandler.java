package epicsquid.superiorshields.network;

import epicsquid.superiorshields.SuperiorShields;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

  public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(SuperiorShields.MODID);
  private static int id = 0;

  public static void registerMessages() {
    INSTANCE.registerMessage(PacketShieldUpdate.Handler.class, PacketShieldUpdate.class, id++, Side.CLIENT);
  }

}
