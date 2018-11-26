package epicsquid.superiorshields.shield.effect;

import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ShieldEffectSpawn<E extends Entity> implements IShieldEffect {

  private Random random = new Random();
  private float chanceToSpawn;
  private Class<E> entityClass;

  public ShieldEffectSpawn(@Nonnull Class<E> entityClass, float chanceToSpawn) {
    this.entityClass = entityClass;
    this.chanceToSpawn = chanceToSpawn;
  }

  @Override
  public void applyEffect(@Nonnull IShieldCapability shield, @Nonnull EntityPlayer player, @Nullable DamageSource source, float damage, EffectTrigger trigger) {
    if (trigger == EffectTrigger.DAMAGE && !player.world.isRemote && random.nextFloat() < chanceToSpawn) {
      try {
        Entity entity = entityClass.getConstructor(World.class).newInstance(player.world);
        entity.setLocationAndAngles(player.posX + random.nextDouble(), player.posY + player.getEyeHeight(), player.posZ + random.nextDouble(), player.rotationYaw,0);
        player.world.spawnEntity(entity);
      } catch (Exception e) {
        // TODO proper exception logging
        System.out.println("Error: Could not create entity.");
      }

    }
  }
}
