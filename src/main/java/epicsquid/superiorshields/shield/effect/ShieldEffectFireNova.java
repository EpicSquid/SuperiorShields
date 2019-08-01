package epicsquid.superiorshields.shield.effect;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.entity.LivingEntity;

public class ShieldEffectFireNova extends ShieldEffectNova {

  private int duration;

  public ShieldEffectFireNova(int duration, double radius) {
    super(radius, "shield.effect.nova.fire");
    this.duration = duration;
  }

  @Override
  protected void applyToEntities(@Nonnull List<LivingEntity> entities) {
    for (LivingEntity entity : entities) {
      entity.setFire(duration);
    }
  }
}
