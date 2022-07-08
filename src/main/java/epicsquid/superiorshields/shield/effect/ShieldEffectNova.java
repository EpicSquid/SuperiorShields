package epicsquid.superiorshields.shield.effect;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class ShieldEffectNova implements IShieldEffect {

	private double radius;
	private String description;

	public ShieldEffectNova(double radius, String description) {
		this.radius = radius;
		this.description = description;
	}

	@Override
	public void applyEffect(@Nonnull IShieldCapability shield, @Nonnull Player player, @Nullable DamageSource source, float damage, EffectTrigger trigger, int level) {
		if (!player.level.isClientSide && trigger == EffectTrigger.EMPTY) {
			var rad = radius * level;
			List<LivingEntity> entities = player.level.getEntitiesOfClass(LivingEntity.class,
							new AABB(player.position().x + rad, player.position().y + rad, player.position().z + rad, player.position().x - rad, player.position().y - rad,
											player.position().z - rad));
			applyToEntities(entities.stream().filter(le -> !(le instanceof Player)).toList());
		}
	}

	protected abstract void applyToEntities(@Nonnull List<LivingEntity> entities);

	@Nonnull
	@Override
	public final String getDescription() {
		return I18n.get(this.description);
	}
}
