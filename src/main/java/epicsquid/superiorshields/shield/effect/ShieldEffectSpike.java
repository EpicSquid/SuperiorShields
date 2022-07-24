package epicsquid.superiorshields.shield.effect;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public abstract class ShieldEffectSpike implements IShieldEffect {

	private final String description;

	public ShieldEffectSpike(String description) {
		this.description = description;
	}

	@Override
	public void applyEffect(@NotNull IShieldCapability shield, @NotNull Player player, @Nullable DamageSource source, float damage, EffectTrigger trigger, int level) {
		if (trigger == EffectTrigger.DAMAGE && source.getEntity() instanceof LivingEntity livingEntity) {
			spike(livingEntity, damage, level);
		}
	}

	protected abstract void spike(@Nonnull LivingEntity livingEntity, float damage, int level);

	@NotNull
	@Override
	public String getDescription() {
		return I18n.get(description);
	}
}
