package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.shield.VanillaShield;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemVanillaShield extends ItemSuperiorShield<VanillaShield> {

  private int damage = 1;

  public ItemVanillaShield(@Nonnull String name, @Nonnull VanillaShield shieldType) {
    super(name, shieldType);
    setMaxDamage(shieldType.getMaxDamage());
    setHasSubtypes(false);
  }

  @Override
  protected boolean useEnergyToRecharge(@Nonnull ItemStack stack, @Nonnull EntityPlayer player) {
    stack.damageItem(damage, player);
    if (stack.getItemDamage() >= shieldType.getMaxDamage()) {
      return false;
    }
    return true;
  }
}
