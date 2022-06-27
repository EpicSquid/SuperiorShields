package epicsquid.superiorshields.capability.shield;

import epicsquid.superiorshields.SuperiorShields;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = SuperiorShields.MODID)
public class CapabilityRegistry {

	public static final String SHIELD_CAP_NAME = "superior_shield";

	public static final Capability<IShieldCapability> SHIELD_CAPABILITY = CapabilityManager.get(new CapabilityToken<IShieldCapability>() {
	});

	public static LazyOptional<IShieldCapability> getShield(final Player player) {
		return player.getCapability(SHIELD_CAPABILITY);
	}

	@SubscribeEvent
	public static void onAttachCapabilities(@Nonnull AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof Player) {
			event.addCapability(new ResourceLocation(SuperiorShields.MODID, SHIELD_CAP_NAME), new ShieldCapabilityProvider((Player) event.getObject()));
		}
	}

	@SubscribeEvent
	public static void onRegisterCapabilities(final RegisterCapabilitiesEvent event) {
		event.register(IShieldCapability.class);
	}
}
