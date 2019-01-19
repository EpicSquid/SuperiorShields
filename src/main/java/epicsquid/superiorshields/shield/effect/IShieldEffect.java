package epicsquid.superiorshields.shield.effect;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

public interface IShieldEffect {

  void applyEffect(@Nonnull IShieldCapability shield, @Nonnull EntityPlayer player, @Nullable DamageSource source, float damage, EffectTrigger trigger);

  @Nonnull
  String getDescription();
}
