package epicsquid.superiorshields.shield.effect;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.entity.EntityLiving;

public class ShieldEffectFireNova extends ShieldEffectNova {

  private int duration;

  public ShieldEffectFireNova(int duration, double radius) {
    super(radius);
    this.duration = duration;
  }

  @Override
  protected void applyToEntities(@Nonnull List<EntityLiving> entities) {
    for (EntityLiving entity : entities) {
      entity.setFire(duration);
    }
  }
}
