package epicsquid.superiorshields.shield.effect;

import epicsquid.superiorshields.config.Config;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ShieldEffectPotionNova extends ShieldEffectNova {

	private final List<MobEffect> effects;
	private final Supplier<Integer> duration;

	public ShieldEffectPotionNova(int duration, float radius, String description, @Nonnull MobEffect... effects) {
		super(radius, description);
		this.effects = Arrays.asList(effects);
		this.duration = () -> duration * 20;
	}

	public ShieldEffectPotionNova(String description, @Nonnull MobEffect... effects) {
		super(description);
		this.effects = Arrays.asList(effects);
		this.duration = Config.SHIELD.NOVA_EFFECT_DURATION;
	}

	@Override
	protected void applyToEntities(@Nonnull List<LivingEntity> entities) {
		for (LivingEntity entity : entities) {
			for (MobEffect effect : effects) {
				entity.addEffect(new MobEffectInstance(effect, duration.get()));
			}
		}
	}
}
