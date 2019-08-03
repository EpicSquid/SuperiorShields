package epicsquid.superiorshields.item;

import epicsquid.superiorshields.shield.VanillaShield;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class ItemVanillaShield extends ItemSuperiorShield<VanillaShield> {

  private int damage = 1;

  public ItemVanillaShield(Properties props, VanillaShield shieldType) {
    super(props, shieldType);
  }

  @Override
  protected boolean useEnergyToRecharge(ItemStack stack, PlayerEntity player) {
    stack.damageItem(damage, player, e -> {});
    return !(stack.getDamage() >= shieldType.getMaxDamage());
  }
}
