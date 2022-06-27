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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EnergyCapabilityProvider implements ICapabilityProvider, INBTSerializable<Tag>, IEnergyStorage {

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
			CompoundTag tag = new CompoundTag();
			tag.putInt("energy", currentEnergy);
			stack.setTag(tag);
		}
		this.op = LazyOptional.of(() -> this);
	}

	@Override
	@Nonnull
	@SuppressWarnings("unchecked")
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
		return capability == CapabilityEnergy.ENERGY ? (LazyOptional<T>) op : LazyOptional.empty();
	}

	@Override
	public Tag serializeNBT() {
		CompoundTag tag = new CompoundTag();
		tag.putInt("maxEnergy", maxEnergy);
		tag.putInt("currentEnergy", currentEnergy);
		tag.putInt("input", input);
		tag.putInt("output", output);
		return tag;
	}

	@Override
	public void deserializeNBT(Tag nbt) {
		if (nbt instanceof CompoundTag tag) {
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
