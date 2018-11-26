package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.capability.shield.SuperiorShieldsCapabilityManager;
import epicsquid.superiorshields.shield.IShieldType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ItemAuraShield extends ItemSuperiorShield<IShieldType> {

  public ItemAuraShield(@Nonnull String name, @Nonnull IShieldType shieldType) {
    super(name, shieldType);
  }

  @Override
  public float applyShield(@Nonnull EntityPlayer player, @Nonnull ItemStack stack, float damage, @Nonnull DamageSource source) {
    if (player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null)) {
      IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
      float absorbed = shield.getCurrentHp() - damage;
      if (shield.getCurrentHp() > 0f && NaturesAuraAPI.instance().extractAuraFromPlayer(player, (int) (5 * (damage + absorbed)), false)) {
        triggerShieldEffect(player, stack, source, damage);
        return absorbDamage(player, shield, absorbed);
      }
    }
    return damage;
  }
}
