package epicsquid.superiorshields.shield.effect;

import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ShieldEffectSpawn<E extends Entity> implements IShieldEffect {

	private Random random = new Random();
	private float chanceToSpawn;
	private Class<E> entityClass;
	private String description;

	public ShieldEffectSpawn(@Nonnull Class<E> entityClass, float chanceToSpawn, String description) {
		this.entityClass = entityClass;
		this.chanceToSpawn = chanceToSpawn;
		this.description = description;
	}

	@Override
	public void applyEffect(@Nonnull IShieldCapability shield, @Nonnull PlayerEntity player, @Nullable DamageSource source, float damage, EffectTrigger trigger) {
		if (trigger == EffectTrigger.DAMAGE && !player.world.isRemote && random.nextFloat() < chanceToSpawn) {
			try {
				Entity entity = entityClass.getConstructor(World.class).newInstance(player.world);
				entity.setLocationAndAngles(player.posX + random.nextDouble(), player.posY + player.getEyeHeight(), player.posZ + random.nextDouble(), player.rotationYaw, 0);
				player.world.addEntity(entity);
			} catch (Exception e) {
				// TODO proper exception logging
				System.out.println("Error: Could not create entity.");
			}

		}
	}

	@Nonnull
	@Override
	public String getDescription() {
		return I18n.format(description);
	}
}
