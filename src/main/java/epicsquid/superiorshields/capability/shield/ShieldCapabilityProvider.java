package epicsquid.superiorshields.capability.shield;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ShieldCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

	private IShieldCapability shield = new ShieldCapability();
	private LazyOptional<IShieldCapability> op;

	public ShieldCapabilityProvider(Player player) {
		if (!player.getCommandSenderWorld().isClientSide && player instanceof ServerPlayer) {
			this.shield = new ShieldCapability();
		}
		this.op = LazyOptional.of(() -> shield);
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction facing) {
		return CapabilityRegistry.SHIELD_CAPABILITY.orEmpty(cap, op);
	}

	@Override
	public CompoundTag serializeNBT() {
		return this.shield.serializeNBT();
	}

	@Override
	public void deserializeNBT(CompoundTag tag) {
		this.shield.deserializeNBT(tag);
	}
}
