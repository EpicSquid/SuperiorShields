package epicsquid.superiorshields.shield.effect;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public abstract class ShieldEffectNova implements IShieldEffect {

	private double radius;
	private String description;

	public ShieldEffectNova(double radius, String description) {
		this.radius = radius;
		this.description = description;
	}

	@Override
	public void applyEffect(@Nonnull IShieldCapability shield, @Nonnull Player player, @Nullable DamageSource source, float damage, EffectTrigger trigger) {
		if (!player.getCommandSenderWorld().isClientSide && trigger == EffectTrigger.EMPTY) {
			List<LivingEntity> entities = player.getCommandSenderWorld().getEntitiesOfClass(LivingEntity.class,
					new AxisAlignedBB(player.position().x + radius, player.position().y + radius, player.position().z + radius, player.position().x - radius, player.position().y - radius,
							player.position().z - radius));

			applyToEntities(entities);
		}
	}

	protected abstract void applyToEntities(@Nonnull List<LivingEntity> entities);

	@Nonnull
	@Override
	public final String getDescription() {
		return I18n.get(this.description);
	}
}
