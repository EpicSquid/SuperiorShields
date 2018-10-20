package epicsquid.superiorshields.capability;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.SuperiorShields;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = "superiorshields")
public class SuperiorShieldsCapabilityManager {

  @CapabilityInject(IShieldCapability.class)
  public static final Capability<IShieldCapability> shieldCapability = null;

  public static void preInit() {
    CapabilityManager.INSTANCE.register(IShieldCapability.class, new ShieldCapabilityStorage(), ShieldCapability.class);
  }

  @SubscribeEvent
  public static void onAddCapabilities(@Nonnull AttachCapabilitiesEvent event) {
    if (event.getObject() instanceof EntityPlayer) {
      event.addCapability(new ResourceLocation(SuperiorShields.MODID, "shieldcapability"), new ShieldCapabilityProvider(true));
    }
  }
}
