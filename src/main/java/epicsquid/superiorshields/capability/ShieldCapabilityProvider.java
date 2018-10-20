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

  private float currentHp;
  private float maxHp;
  private int timeWithoutDamage;

  public ShieldCapabilityProvider(boolean isNewShield) {
    if (isNewShield) {
      currentHp = 0;
      maxHp = 0;
      timeWithoutDamage = 0;
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
    tag.setInteger(ShieldCapabilityStorage.NBT_TIME_WITHOUT_DAMAGE, getTimeWithoutDamage());
    return tag;
  }

  @Override
  public void deserializeNBT(NBTBase nbt) {
    if (nbt instanceof NBTTagCompound) {
      NBTTagCompound tag = (NBTTagCompound) nbt;
      if (tag.hasKey(ShieldCapabilityStorage.NBT_MAX_SHIELD_HP)) {
        setMaxHp(tag.getFloat(ShieldCapabilityStorage.NBT_MAX_SHIELD_HP));
      }
      if (tag.hasKey(ShieldCapabilityStorage.NBT_SHIELD_HP)) {
        setCurrentHp(tag.getFloat(ShieldCapabilityStorage.NBT_SHIELD_HP));
      }
      if (tag.hasKey(ShieldCapabilityStorage.NBT_TIME_WITHOUT_DAMAGE)) {
        setTimeWithoutDamage(tag.getInteger(ShieldCapabilityStorage.NBT_TIME_WITHOUT_DAMAGE));
      }
    }
  }

  @Override
  public float getMaxHp() {
    return maxHp;
  }

  @Override
  public float getCurrentHp() {
    return currentHp;
  }

  @Override
  public int getTimeWithoutDamage() {
    return timeWithoutDamage;
  }

  @Override
  public void setCurrentHp(float currentHp) {
    this.currentHp = currentHp;
    if (this.currentHp < 0) {
      this.currentHp = 0;
    } else if (this.currentHp > maxHp) {
      this.currentHp = maxHp;
    }
  }

  @Override
  public void setMaxHp(float maxHp) {
    this.maxHp = maxHp;
  }

  @Override
  public void setTimeWithoutDamage(int timeWithoutDamage) {
    this.timeWithoutDamage = timeWithoutDamage;
  }
}
