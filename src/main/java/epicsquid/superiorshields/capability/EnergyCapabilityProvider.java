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

	private int maxEnergy;
	private int currentEnergy;
	private int inputRate;
	private int outputRate;
	private final ItemStack stack;
	private final LazyOptional<EnergyCapabilityProvider> op;

	public EnergyCapabilityProvider(int maxEnergy, int currentEnergy, int inputRate, int outputRate, @Nonnull ItemStack stack) {
		this.maxEnergy = maxEnergy;
		this.currentEnergy = currentEnergy;
		this.inputRate = inputRate;
		this.outputRate = outputRate;
		this.stack = stack;

		if (stack.getTag() == null) {
			CompoundTag tag = new CompoundTag();
			tag.putInt("energy", currentEnergy);
			stack.setTag(tag);
		}
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
		tag.putInt("maxEnergy", maxEnergy);
		tag.putInt("currentEnergy", currentEnergy);
		tag.putInt("inputRate", inputRate);
		tag.putInt("outputRate", outputRate);
		return tag;
	}

	@Override
	public void deserializeNBT(Tag nbt) {
		if (nbt instanceof CompoundTag tag) {
			maxEnergy = tag.getInt("maxEnergy");
			currentEnergy = tag.getInt("currentEnergy");
			inputRate = tag.getInt("inputRate");
			outputRate = tag.getInt("outputRate");
		}
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		int energyToUpdate = Math.min(currentEnergy + maxReceive, maxEnergy);
		if (!simulate) {
			currentEnergy = energyToUpdate;
			if (stack.getTag() != null) {
				stack.getTag().putInt("energy", currentEnergy);
			}
		}
		return maxReceive;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		int energyToUpdate = Math.max(currentEnergy - maxExtract, 0);
		if (!simulate) {
			currentEnergy = energyToUpdate;
			if (stack.getTag() != null) {
				stack.getTag().putInt("energy", currentEnergy);
			}
		}
		return maxExtract;
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
