package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import epicsquid.superiorshields.shield.IShieldType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemAuraShield extends ItemSuperiorShield<IShieldType> {

  private int auraToConsume = 10;

  public ItemAuraShield(@Nonnull String name, @Nonnull IShieldType shieldType) {
    super(name, shieldType);
  }

  @Override
  protected boolean useEnergyToRecharge(@Nonnull ItemStack stack, @Nonnull EntityPlayer player) {
    return NaturesAuraAPI.instance().extractAuraFromPlayer(player, auraToConsume, false);
  }
}
