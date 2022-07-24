package epicsquid.superiorshields.shield.effect;

import epicsquid.superiorshields.config.Config;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ShieldEffectSpikePotion extends ShieldEffectSpike {

	private final List<MobEffect> effects;

	private final Supplier<Integer> duration;

	public ShieldEffectSpikePotion(String description, int duration, MobEffect... effects) {
		super(description);
		this.duration = () -> duration * 20;
		this.effects = Arrays.asList(effects);
	}

	public ShieldEffectSpikePotion(String description, MobEffect... effects) {
		super(description);
		this.effects = Arrays.asList(effects);
		this.duration = Config.SHIELD.SPIKE_EFFECT_DURATION;
	}

	@Override
	protected void spike(@NotNull LivingEntity livingEntity, float damage, int level) {
		for (MobEffect effect : effects) {
			livingEntity.addEffect(new MobEffectInstance(effect, duration.get() * 20, level));
		}
	}
}
