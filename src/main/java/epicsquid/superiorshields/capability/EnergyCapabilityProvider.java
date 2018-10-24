package epicsquid.superiorshields.capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class EnergyCapabilityProvider implements ICapabilityProvider, INBTSerializable, IEnergyStorage {

  private int maxEnergy;
  private int currentEnergy;
  private int input;
  private int output;
  private ItemStack stack;

  public EnergyCapabilityProvider(int maxEnergy, int currentEnergy, int input, int output, @Nonnull ItemStack stack) {
    this.maxEnergy = maxEnergy > 0 ? maxEnergy : 0;
    this.currentEnergy = currentEnergy > 0 ? currentEnergy : 0;
    this.input = input > 0 ? input : 0;
    this.output = output > 0 ? output : 0;
    this.stack = stack;
    if (stack.getTagCompound() == null) {
      NBTTagCompound tag = new NBTTagCompound();
      tag.setInteger("energy", currentEnergy);
      stack.setTagCompound(tag);
    }
  }

  @Override
  public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
    return capability == CapabilityEnergy.ENERGY;
  }

  @Nullable
  @Override
  @SuppressWarnings("unchecked")
  public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
    if (capability == CapabilityEnergy.ENERGY) {
      return (T) this;
    }
    return null;
  }

  @Override
  public NBTBase serializeNBT() {
    NBTTagCompound tag = new NBTTagCompound();
    tag.setInteger("maxEnergy", maxEnergy);
    tag.setInteger("currentEnergy", currentEnergy);
    tag.setInteger("input", input);
    tag.setInteger("output", output);
    return tag;
  }

  @Override
  public void deserializeNBT(NBTBase nbt) {
    if (nbt instanceof NBTTagCompound) {
      NBTTagCompound tag = (NBTTagCompound) nbt;
      maxEnergy = tag.getInteger("maxEnergy");
      currentEnergy = tag.getInteger("currentEnergy");
      input = tag.getInteger("input");
      output = tag.getInteger("output");
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
      stack.getTagCompound().setInteger("energy", currentEnergy);
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
      stack.getTagCompound().setInteger("energy", currentEnergy);
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
