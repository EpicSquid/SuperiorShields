package epicsquid.superiorshields.shield.effect;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ShieldEffectPotionNova extends ShieldEffectNova {

  private Potion effect;
  private int duration;

  public ShieldEffectPotionNova(@Nonnull Potion effect, int duration, double radius) {
    super(radius);
    this.effect = effect;
    this.duration = duration;
  }

  @Override
  protected void applyToEntities(@Nonnull List<EntityLiving> entities) {
    for (EntityLiving entity : entities) {
      entity.addPotionEffect(new PotionEffect(effect, duration));
    }
  }
}
