package epicsquid.superiorshields.shield.effect;

import epicsquid.superiorshields.config.Config;
import epicsquid.superiorshields.lang.ModLang;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Supplier;

public class ShieldEffectFireNova extends ShieldEffectNova {

	private final Supplier<Integer> duration;

	public ShieldEffectFireNova(int duration, float radius) {
		super(radius, ModLang.FIRE_NOVA.getString());
		this.duration = () -> duration;
	}

	public ShieldEffectFireNova() {
		super(ModLang.FIRE_NOVA.getString());
		this.duration = Config.SHIELD.NOVA_EFFECT_DURATION;
	}

	@Override
	protected void applyToEntities(@Nonnull List<LivingEntity> entities) {
		for (LivingEntity entity : entities) {
			entity.setSecondsOnFire(duration.get());
		}
	}
}
