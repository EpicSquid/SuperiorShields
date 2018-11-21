package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import epicsquid.superiorshields.capability.IShieldCapability;
import epicsquid.superiorshields.capability.SuperiorShieldsCapabilityManager;
import epicsquid.superiorshields.shield.IShieldType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAuraShield extends ItemSuperiorShield<IShieldType> {

  public ItemAuraShield(@Nonnull String name, @Nonnull IShieldType shieldType) {
    super(name, shieldType);
  }

  @Override
  public float applyShield(@Nonnull EntityPlayer player, @Nonnull ItemStack stack, float damage) {
    if (player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null)) {
      IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
      float absorbed = shield.getCurrentHp() - damage;
      if (shield.getCurrentHp() > 0f && NaturesAuraAPI.instance().extractAuraFromPlayer(player, (int) (5 * (damage + absorbed)), false)) {
        shield.setCurrentHp(absorbed);
        resetShieldDelay(shield);
        updateClient(player, shield);
        return absorbed < 0f ? -1f * absorbed : 0f;
      }
    }
    return damage;
  }
}
