package epicsquid.superiorshields.capability.shield;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.SuperiorShields;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "superiorshields")
public class SuperiorShieldsCapabilityManager {

	@CapabilityInject(IShieldCapability.class) public static final Capability<IShieldCapability> shieldCapability = null;

	public static void init() {
		CapabilityManager.INSTANCE.register(IShieldCapability.class, new ShieldCapabilityStorage(), ShieldCapability::new);
	}

	@SubscribeEvent
	public static void onAddCapabilities(@Nonnull AttachCapabilitiesEvent event) {
		if (event.getObject() instanceof PlayerEntity) {
			event.addCapability(new ResourceLocation(SuperiorShields.MODID, "shieldcapability"), new ShieldCapabilityProvider(true));
		}
	}
}
