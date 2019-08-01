package epicsquid.superiorshields.shield.effect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class ShieldEffectPotionNova extends ShieldEffectNova {

  private List<Effect> effects;
  private int duration;

  public ShieldEffectPotionNova(@Nonnull Effect effect, int duration, double radius, String description) {
    super(radius, description);
    this.effects = new ArrayList<>();
    effects.add(effect);
    this.duration = duration;
  }

  public ShieldEffectPotionNova(int duration, double radius, String description, @Nonnull Effect... effects) {
    super(radius, description);
    this.effects = Arrays.asList(effects);
    this.duration = duration;
  }

  @Override
  protected void applyToEntities(@Nonnull List<LivingEntity> entities) {
    for (LivingEntity entity : entities) {
      for (Effect effect : effects) {
        entity.addPotionEffect(new EffectInstance(effect, duration));
      }
    }
  }
}
