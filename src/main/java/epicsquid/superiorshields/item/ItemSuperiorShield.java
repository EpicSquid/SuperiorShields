package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import epicsquid.mysticallib.item.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemSuperiorShield extends ItemBase implements IShield, IBauble {

  public ItemSuperiorShield(@Nonnull String name) {
    super(name);
  }

  @Override
  public void applyShield(@Nonnull EntityPlayer player, float damage) {
    System.out.println("Player took: " + damage);
  }

  @Override
  public BaubleType getBaubleType(ItemStack itemStack) {
    return BaubleType.BELT;
  }
}
