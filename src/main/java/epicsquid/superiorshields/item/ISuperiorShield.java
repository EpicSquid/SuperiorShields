package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;

public interface ISuperiorShield {

  float applyShield(@Nonnull EntityPlayer player, float damage);
}
