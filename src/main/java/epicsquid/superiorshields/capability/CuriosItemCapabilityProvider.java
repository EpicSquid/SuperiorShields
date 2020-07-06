package epicsquid.superiorshields.capability;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CuriosItemCapabilityProvider implements ICapabilityProvider {

	final LazyOptional<ICurio> capability;

	public CuriosItemCapabilityProvider(ICurio curio) {
		this.capability = LazyOptional.of(() -> curio);
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		return CuriosCapability.ITEM.orEmpty(cap, capability);
	}
}
