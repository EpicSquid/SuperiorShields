package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public interface ISuperiorShield {

  float applyShield(@Nonnull EntityPlayer player, @Nonnull ItemStack stack, float damage, @Nonnull DamageSource source);

  void rechargeShield(@Nonnull IShieldCapability shield, @Nonnull ItemStack stack, @Nonnull EntityPlayer player);

  void resetShieldDelay(@Nonnull IShieldCapability shield);
}
