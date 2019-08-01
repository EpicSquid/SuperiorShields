package epicsquid.superiorshields.shield.effect;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;

public class ShieldEffectPotion implements IShieldEffect {

	private Effect effect;
	private int duration;
	private EffectTrigger trigger;
	private String description;

	public ShieldEffectPotion(@Nonnull Effect effect, @Nonnull EffectTrigger trigger, int duration, String description) {
		this.effect = effect;
		this.trigger = trigger;
		this.duration = duration;
		this.description = description;
	}

	@Override
	public void applyEffect(@Nonnull IShieldCapability shield, @Nonnull PlayerEntity player, @Nullable DamageSource source, float damage, EffectTrigger trigger) {
		if (trigger == this.trigger) {
			player.addPotionEffect(new EffectInstance(effect, duration));
		}
	}

	@Nonnull
	@Override
	public String getDescription() {
		return I18n.format(description);
	}
}
