package epicsquid.superiorshields.shield.effect;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.entity.EntityLiving;

public class ShieldEffectKnockbackNova extends ShieldEffectNova {

  private float strength;
  private float x;
  private float z;

  public ShieldEffectKnockbackNova(double radius, float strength, float x, float z) {
    super(radius);
    this.strength = strength;
    this.x = x;
    this.z = z;
  }

  @Override
  protected void applyToEntities(@Nonnull List<EntityLiving> entities) {
    for (EntityLiving entity : entities) {
      entity.knockBack(entity, strength, x, z);
    }
  }
}
