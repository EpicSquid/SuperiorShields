package epicsquid.superiorshields.capability.shield;

import epicsquid.superiorshields.SuperiorShields;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.LazyOptional;

public class CapabilityRegistry {

	public static final ResourceLocation SHIELD_CAP_ID = new ResourceLocation(SuperiorShields.MODID, "superior_shield");

	public static final Capability<IShieldCapability> SHIELD_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
	});

	public static LazyOptional<IShieldCapability> getShield(final Player player) {
		return player.getCapability(SHIELD_CAPABILITY);
	}
}
