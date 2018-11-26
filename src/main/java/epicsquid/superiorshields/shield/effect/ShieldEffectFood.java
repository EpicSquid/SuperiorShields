package epicsquid.superiorshields.shield.effect;

import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;

public class ShieldEffectFood implements IShieldEffect {

  private Random random = new Random();
  private float chanceToFeed;
  private int foodLevel;
  private float saturationModifier;

  public ShieldEffectFood(float chanceToFeed, int foodLevel, float saturationModifier) {
    this.chanceToFeed = chanceToFeed;
    this.foodLevel = foodLevel;
    this.saturationModifier = saturationModifier;
  }

  @Override
  public void applyEffect(@Nonnull IShieldCapability shield, @Nonnull EntityPlayer player, @Nullable DamageSource source, float damage, EffectTrigger trigger) {
    if (trigger == EffectTrigger.DAMAGE && random.nextFloat() < chanceToFeed) {
      FoodStats food = player.getFoodStats();
      if (food.needFood()) {
        food.addStats(foodLevel, saturationModifier);
      }
    }
  }
}
