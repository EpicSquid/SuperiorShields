package epicsquid.superiorshields.capability;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EnergyCapabilityProvider implements ICapabilityProvider, INBTSerializable, IEnergyStorage {

	private int maxEnergy;
	private int currentEnergy;
	private int input;
	private int output;
	private ItemStack stack;
	private LazyOptional<EnergyCapabilityProvider> op;

	public EnergyCapabilityProvider(int maxEnergy, int currentEnergy, int input, int output, @Nonnull ItemStack stack) {
		this.maxEnergy = Math.max(maxEnergy, 0);
		this.currentEnergy = Math.max(currentEnergy, 0);
		this.input = Math.max(input, 0);
		this.output = Math.max(output, 0);
		this.stack = stack;
		if (stack.getTag() == null) {
			CompoundNBT tag = new CompoundNBT();
			tag.putInt("energy", currentEnergy);
			stack.setTag(tag);
		}
		this.op = LazyOptional.of(() -> this);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
		return capability == CapabilityEnergy.ENERGY ? (LazyOptional<T>) op : LazyOptional.empty();
	}

	@Override
	public INBT serializeNBT() {
		CompoundNBT tag = new CompoundNBT();
		tag.putInt("maxEnergy", maxEnergy);
		tag.putInt("currentEnergy", currentEnergy);
		tag.putInt("input", input);
		tag.putInt("output", output);
		return tag;
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		if (nbt instanceof CompoundNBT) {
			CompoundNBT tag = (CompoundNBT) nbt;
			maxEnergy = tag.getInt("maxEnergy");
			currentEnergy = tag.getInt("currentEnergy");
			input = tag.getInt("input");
			output = tag.getInt("output");
		}
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		int energyToUpdate = currentEnergy;
		energyToUpdate += maxReceive;
		if (energyToUpdate > maxEnergy) {
			return 0;
		}
		if (!simulate) {
			currentEnergy = energyToUpdate;
			stack.getTag().putInt("energy", currentEnergy);
		}
		return maxReceive;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		int energyToUpdate = currentEnergy;
		energyToUpdate -= maxExtract;
		if (energyToUpdate < 0) {
			return 0;
		}
		if (!simulate) {
			currentEnergy = energyToUpdate;
			stack.getTag().putInt("energy", currentEnergy);
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
