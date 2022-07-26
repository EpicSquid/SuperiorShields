package epicsquid.superiorshields.shield.effect;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ShieldEffectPotion implements IShieldEffect {

	private MobEffect effect;
	private int duration;
	private EffectTrigger trigger;
	private String description;

	public ShieldEffectPotion(@Nonnull MobEffect effect, @Nonnull EffectTrigger trigger, int duration, String description) {
		this.effect = effect;
		this.trigger = trigger;
		this.duration = duration * 20;
		this.description = description;
	}

	@Override
	public void applyEffect(@Nonnull IShieldCapability shield, @Nonnull Player player, @Nullable DamageSource source, float damage, EffectTrigger trigger, int level) {
		if (trigger == this.trigger) {
			player.addEffect(new MobEffectInstance(effect, duration * level));
		}
	}

	@Nonnull
	@Override
	public String getDescription() {
		return I18n.get(description);
	}
}
