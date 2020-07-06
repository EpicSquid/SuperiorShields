package epicsquid.superiorshields.capability.shield;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ShieldCapabilityProvider implements ICapabilityProvider, INBTSerializable<INBT> {

	private IShieldCapability shield = new ShieldCapability();
	private LazyOptional<IShieldCapability> op;

	public ShieldCapabilityProvider(PlayerEntity player) {
		if (!player.getEntityWorld().isRemote && player instanceof ServerPlayerEntity) {
			this.shield = new ShieldCapability();
		}
		this.op = LazyOptional.of(() -> shield);
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
		return getCapability(capability);
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
		return SuperiorShieldsCapabilityManager.shieldCapability.orEmpty(cap, op);
	}

	@Override
	public INBT serializeNBT() {
		return SuperiorShieldsCapabilityManager.shieldCapability.writeNBT(shield, null);
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		SuperiorShieldsCapabilityManager.shieldCapability.readNBT(shield, null, nbt);
	}
}
