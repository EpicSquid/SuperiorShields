package epicsquid.superiorshields.capability.shield;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ShieldCapabilityStorage implements IStorage<IShieldCapability> {

  public static final String NBT_MAX_SHIELD_HP = "maxShieldHp";
  public static final String NBT_SHIELD_HP = "shieldHp";
  public static final String NBT_TIME_WITHOUT_DAMAGE = "timeWithoutDamage";

  @Nullable
  @Override
  public NBTBase writeNBT(Capability<IShieldCapability> capability, IShieldCapability instance, EnumFacing side) {
    NBTTagCompound tag = new NBTTagCompound();
    tag.setFloat(NBT_MAX_SHIELD_HP, instance.getMaxHp());
    tag.setFloat(NBT_SHIELD_HP, instance.getCurrentHp());
    tag.setInteger(NBT_TIME_WITHOUT_DAMAGE, instance.getTimeWithoutDamage());
    return tag;
  }

  @Override
  public void readNBT(Capability<IShieldCapability> capability, IShieldCapability instance, EnumFacing side, NBTBase nbt) {
    if (nbt instanceof NBTTagCompound) {
      NBTTagCompound tag = (NBTTagCompound) nbt;
      if (tag.hasKey(NBT_MAX_SHIELD_HP)) {
        instance.setMaxHp(tag.getFloat(NBT_MAX_SHIELD_HP));
      }
      if (tag.hasKey(NBT_SHIELD_HP)) {
        instance.setCurrentHp(tag.getFloat(NBT_SHIELD_HP));
      }
      if (tag.hasKey(NBT_TIME_WITHOUT_DAMAGE)) {
        instance.setTimeWithoutDamage(tag.getInteger(NBT_TIME_WITHOUT_DAMAGE));
      }
    }
  }
}
