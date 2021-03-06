package epicsquid.superiorshields.shield.effect;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
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
  public void applyEffect(@Nonnull IShieldCapability shield, @Nonnull EntityPlayer player, @Nullable DamageSource source, float damage, EffectTrigger trigger) {
    if (!player.world.isRemote && trigger == EffectTrigger.EMPTY) {
      List<EntityLiving> entities = player.world.getEntitiesWithinAABB(EntityLiving.class,
          new AxisAlignedBB(player.posX + radius, player.posY + radius, player.posZ + radius, player.posX - radius, player.posY - radius,
              player.posZ - radius));

      applyToEntities(entities);
    }
  }

  protected abstract void applyToEntities(@Nonnull List<EntityLiving> entities);

  @Nonnull
  @Override
  public final String getDescription() {
    return I18n.format(this.description);
  }
}
