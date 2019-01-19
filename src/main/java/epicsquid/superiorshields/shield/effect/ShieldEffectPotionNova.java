package epicsquid.superiorshields.shield.effect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ShieldEffectPotionNova extends ShieldEffectNova {

  private List<Potion> effects;
  private int duration;

  public ShieldEffectPotionNova(@Nonnull Potion effect, int duration, double radius, String description) {
    super(radius, description);
    this.effects = new ArrayList<>();
    effects.add(effect);
    this.duration = duration;
  }

  public ShieldEffectPotionNova(int duration, double radius, String description, @Nonnull Potion... effects) {
    super(radius, description);
    this.effects = Arrays.asList(effects);
    this.duration = duration;
  }

  @Override
  protected void applyToEntities(@Nonnull List<EntityLiving> entities) {
    for (EntityLiving entity : entities) {
      for (Potion effect : effects) {
        entity.addPotionEffect(new PotionEffect(effect, duration));
      }
    }
  }
}
