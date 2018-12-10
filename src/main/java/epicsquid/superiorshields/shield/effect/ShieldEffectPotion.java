package epicsquid.superiorshields.shield.effect;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public class ShieldEffectPotion implements IShieldEffect {

  private Potion effect;
  private int duration;
  private EffectTrigger trigger;

  public ShieldEffectPotion(@Nonnull Potion effect, @Nonnull EffectTrigger trigger, int duration) {
    this.effect = effect;
    this.trigger = trigger;
    this.duration = duration;
  }

  @Override
  public void applyEffect(@Nonnull IShieldCapability shield, @Nonnull EntityPlayer player, @Nullable DamageSource source, float damage, EffectTrigger trigger) {
    if (trigger == this.trigger) {
      player.addPotionEffect(new PotionEffect(effect, duration));
    }
  }
}
