package epicsquid.superiorshields.capability.shield;

import epicsquid.superiorshields.SuperiorShields;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = "superiorshields")
public class SuperiorShieldsCapabilityManager {

	public static final String ShieldCapabilityName = "superior_shield";

	@CapabilityInject(IShieldCapability.class)
	public static final Capability<IShieldCapability> shieldCapability = null;

	public static void init() {
		CapabilityManager.INSTANCE.register(IShieldCapability.class, new ShieldCapabilityStorage(), ShieldCapability::new);
	}

	@SubscribeEvent
	public static void onAddCapabilities(@Nonnull AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof PlayerEntity) {
			event.addCapability(new ResourceLocation(SuperiorShields.MODID, ShieldCapabilityName), new ShieldCapabilityProvider((PlayerEntity) event.getObject()));
		}
	}
}
