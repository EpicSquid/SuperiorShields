package epicsquid.superiorshields.shield.effect;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class ShieldEffectFood implements IShieldEffect {

    private final Random random = new Random();
    private final float chanceToFeed;
    private final int foodLevel;
    private final float saturationModifier;

    public ShieldEffectFood(float chanceToFeed, int foodLevel, float saturationModifier) {
        this.chanceToFeed = chanceToFeed;
        this.foodLevel = foodLevel;
        this.saturationModifier = saturationModifier;
    }

    @Override
    public void applyEffect(@Nonnull IShieldCapability shield, @Nonnull Player player, @Nullable DamageSource source, float damage, EffectTrigger trigger, int level) {
        if (trigger == EffectTrigger.DAMAGE && random.nextFloat() < chanceToFeed) {
            FoodData food = player.getFoodData();
            if (food.needsFood()) {
                food.eat(foodLevel * level, saturationModifier);
            }
        }
    }

    @Nonnull
    @Override
    public String getDescription() {
        return I18n.get("shield.effect.food");
    }
}
