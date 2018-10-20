package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.capability.IShieldCapability;
import net.minecraft.entity.player.EntityPlayer;

public interface ISuperiorShield {

  float applyShield(@Nonnull EntityPlayer player, float damage);

  void rechargeShield(@Nonnull IShieldCapability shield);

  void resetShieldDelay(@Nonnull IShieldCapability shield);

  float getMaxShieldHp();

  int getShieldRechargeDelay();

  int getShieldRechargeRate();
}
