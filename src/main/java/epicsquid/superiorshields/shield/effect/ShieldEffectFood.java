package epicsquid.superiorshields.shield.effect;

import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
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
	public void applyEffect(@Nonnull IShieldCapability shield, @Nonnull PlayerEntity player, @Nullable DamageSource source, float damage, EffectTrigger trigger) {
		if (trigger == EffectTrigger.DAMAGE && random.nextFloat() < chanceToFeed) {
			FoodStats food = player.getFoodData();
			if (food.needsFood()) {
				food.eat(foodLevel, saturationModifier);
			}
		}
	}

	@Nonnull
	@Override
	public String getDescription() {
		return I18n.get("shield.effect.food");
	}
}
