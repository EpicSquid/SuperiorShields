package epicsquid.superiorshields.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class EnergyCapabilityProvider implements ICapabilityProvider, INBTSerializable<Tag>, IEnergyStorage {

	private final int maxEnergy;
	private int currentEnergy;
	private final int inputRate;
	private final int outputRate;
	private final ItemStack stack;
	private final LazyOptional<IEnergyStorage> op;

	public EnergyCapabilityProvider(int maxEnergy, int currentEnergy, int inputRate, int outputRate, @Nonnull ItemStack stack) {
		this.maxEnergy = maxEnergy;
		this.inputRate = inputRate;
		this.outputRate = outputRate;
		this.stack = stack;

		this.currentEnergy = stack.getOrCreateTag().getInt("energy");
		this.op = LazyOptional.of(() -> this);
	}

	@NotNull
	@Override
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		return cap == CapabilityEnergy.ENERGY ? op.cast() : LazyOptional.empty();
	}

	@Override
	public Tag serializeNBT() {
		CompoundTag tag = new CompoundTag();
		tag.putInt("energy", currentEnergy);
		return tag;
	}

	@Override
	public void deserializeNBT(Tag nbt) {
		if (nbt instanceof CompoundTag tag) {
			currentEnergy = tag.getInt("energy");
		}
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		int energyToUpdate = Math.min(Math.min(maxReceive, inputRate), maxEnergy - currentEnergy);
		if (!simulate) {
			currentEnergy += energyToUpdate;
			stack.getOrCreateTag().putInt("energy", currentEnergy);
		}
		return energyToUpdate;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		int energyToUpdate = Math.min(Math.min(maxExtract, outputRate), currentEnergy);
		if (!simulate) {
			currentEnergy -= energyToUpdate;
			stack.getOrCreateTag().putInt("energy", currentEnergy);
		}
		return energyToUpdate;
	}

	@Override
	public int getEnergyStored() {
		return currentEnergy;
	}

	@Override
	public int getMaxEnergyStored() {
		return maxEnergy;
	}

	@Override
	public boolean canExtract() {
		return true;
	}

	@Override
	public boolean canReceive() {
		return true;
	}
}
