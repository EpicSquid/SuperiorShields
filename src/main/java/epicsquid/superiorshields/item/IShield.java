package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;

public interface IShield {

  void applyShield(@Nonnull EntityPlayer player, float damage);
}
