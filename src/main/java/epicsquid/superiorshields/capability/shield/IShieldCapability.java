package epicsquid.superiorshields.capability.shield;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IShieldCapability extends INBTSerializable<CompoundTag> {

  float getCurrentHp();

  float getMaxHp();

  int getTimeWithoutDamage();

  void setCurrentHp(float currentHp);

  void setMaxHp(float maxHp);

  void setTimeWithoutDamage(int time);
}
