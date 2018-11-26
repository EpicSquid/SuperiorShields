package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.capability.shield.SuperiorShieldsCapabilityManager;
import epicsquid.superiorshields.shield.IShieldType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import vazkii.botania.api.mana.ManaItemHandler;

public class ItemManaShield extends ItemSuperiorShield<IShieldType> {

  public ItemManaShield(@Nonnull String name, @Nonnull IShieldType shieldType) {
    super(name, shieldType);
  }

  @Override
  public float applyShield(@Nonnull EntityPlayer player, @Nonnull ItemStack stack, float damage, @Nonnull DamageSource source) {
    if (player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null)) {
      IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
      float absorbed = shield.getCurrentHp() - damage;
      if (shield.getCurrentHp() > 0f && ManaItemHandler.requestManaExact(stack, player, (int) (200 * (damage + absorbed)), true)) {
        triggerShieldEffect(player, stack, source, damage);
        return absorbDamage(player, shield, absorbed);
      }
    }
    return damage;
  }
}
