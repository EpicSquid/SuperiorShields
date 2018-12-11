package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.shield.IShieldType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.ManaItemHandler;

public class ItemManaShield extends ItemSuperiorShield<IShieldType> {

  private int manaToConsume = 400;

  public ItemManaShield(@Nonnull String name, @Nonnull IShieldType shieldType) {
    super(name, shieldType);
  }

  @Override
  protected boolean useEnergyToRecharge(@Nonnull ItemStack stack, @Nonnull EntityPlayer player) {
    return ManaItemHandler.requestManaExact(stack, player, manaToConsume, true);
  }
}
