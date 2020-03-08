package epicsquid.superiorshields.shield.effect;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;

public abstract class ShieldEffectNova implements IShieldEffect {

	private double radius;
	private String description;

	public ShieldEffectNova(double radius, String description) {
		this.radius = radius;
		this.description = description;
	}

	@Override
	public void applyEffect(@Nonnull IShieldCapability shield, @Nonnull PlayerEntity player, @Nullable DamageSource source, float damage, EffectTrigger trigger) {
		if (!player.world.isRemote && trigger == EffectTrigger.EMPTY) {
			List<LivingEntity> entities = player.world.getEntitiesWithinAABB(LivingEntity.class,
					new AxisAlignedBB(player.getPosX() + radius, player.getPosY() + radius, player.getPosZ() + radius, player.getPosX() - radius, player.getPosY() - radius,
							player.getPosZ() - radius));

			applyToEntities(entities);
		}
	}

	protected abstract void applyToEntities(@Nonnull List<LivingEntity> entities);

	@Nonnull
	@Override
	public final String getDescription() {
		return I18n.format(this.description);
	}
}
