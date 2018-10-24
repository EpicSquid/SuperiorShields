package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.capability.IShieldCapability;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ISuperiorShield {

  float applyShield(@Nonnull EntityPlayer player, @Nonnull ItemStack stack, float damage);

  void rechargeShield(@Nonnull IShieldCapability shield);

  void resetShieldDelay(@Nonnull IShieldCapability shield);
}
