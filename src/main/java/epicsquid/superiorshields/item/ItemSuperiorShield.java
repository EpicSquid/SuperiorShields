package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.superiorshields.capability.IShieldCapability;
import epicsquid.superiorshields.capability.SuperiorShieldsCapabilityManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemSuperiorShield extends ItemBase implements ISuperiorShield, IBauble {

  public ItemSuperiorShield(@Nonnull String name) {
    super(name);
  }

  @Override
  public float applyShield(@Nonnull EntityPlayer player, float damage) {
    if (player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null)) {
      IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
      float absorbed = shield.getCurrentHp() - damage;
      shield.setCurrentHp(absorbed);
      return absorbed < 0f ? -1f * absorbed : 0f;
    }
    return damage;
  }

  @Override
  public BaubleType getBaubleType(ItemStack itemStack) {
    return BaubleType.BELT;
  }

  @Override
  public void onEquipped(@Nonnull ItemStack itemstack, @Nonnull EntityLivingBase player) {
    if (player instanceof EntityPlayer && player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null)) {
      IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
      shield.setMaxHp(3.0f);
      shield.setCurrentHp(3.0f);
    }
  }

  @Override
  public void onUnequipped(@Nonnull ItemStack itemstack, @Nonnull EntityLivingBase player) {
    if (player instanceof EntityPlayer && player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null)) {
      IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
      shield.setMaxHp(0.0f);
      shield.setCurrentHp(0.0f);
    }
  }
}
