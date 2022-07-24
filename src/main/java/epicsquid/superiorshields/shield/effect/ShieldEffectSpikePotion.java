package epicsquid.superiorshields.shield.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class ShieldEffectSpikePotion extends ShieldEffectSpike {

	private final List<MobEffect> effects;

	private final int duration;

	public ShieldEffectSpikePotion(String description, int duration, MobEffect... effects) {
		super(description);
		this.duration = duration * 20;
		this.effects = Arrays.asList(effects);
	}

	@Override
	protected void spike(@NotNull LivingEntity livingEntity, float damage, int level) {
		for (MobEffect effect : effects) {
			livingEntity.addEffect(new MobEffectInstance(effect, duration * 20, level));
		}
	}
}
