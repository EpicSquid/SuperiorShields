package epicsquid.superiorshields.capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public class ShieldCapabilityProvider implements ICapabilityProvider, INBTSerializable, IShieldCapability {

  int currentHp;
  int maxHp;

  public ShieldCapabilityProvider(boolean isNewShield) {
    if (isNewShield) {
      currentHp = 0;
      maxHp = 0;
    }
  }

  @Override
  public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
    return SuperiorShieldsCapabilityManager.shieldCapability != null && capability == SuperiorShieldsCapabilityManager.shieldCapability;
  }

  @Nullable
  @Override
  public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
    if (capability == SuperiorShieldsCapabilityManager.shieldCapability) {
      return (T) this;
    }
    return null;
  }

  @Override
  public NBTBase serializeNBT() {
    NBTTagCompound tag = new NBTTagCompound();
    tag.setFloat(ShieldCapabilityStorage.NBT_MAX_SHIELD_HP, getMaxHp());
    tag.setFloat(ShieldCapabilityStorage.NBT_SHIELD_HP, getCurrentHp());
    return tag;
  }

  @Override
  public void deserializeNBT(NBTBase nbt) {
    if (nbt instanceof NBTTagCompound) {
      NBTTagCompound tag = (NBTTagCompound) nbt;
      if (tag.hasKey(ShieldCapabilityStorage.NBT_MAX_SHIELD_HP)) {
        setMaxHp(tag.getInteger(ShieldCapabilityStorage.NBT_MAX_SHIELD_HP));
      }
      if (tag.hasKey(ShieldCapabilityStorage.NBT_SHIELD_HP)) {
        setCurrentHp(tag.getInteger(ShieldCapabilityStorage.NBT_SHIELD_HP));
      }
    }
  }

  @Override
  public int getMaxHp() {
    return maxHp;
  }

  @Override
  public int getCurrentHp() {
    return currentHp;
  }

  @Override
  public void setMaxHp(int currentHp) {
    this.currentHp = currentHp;
  }

  @Override
  public void setCurrentHp(int maxHp) {
    this.maxHp = maxHp;
  }
}
