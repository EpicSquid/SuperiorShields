package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.superiorshields.capability.IShieldCapability;
import epicsquid.superiorshields.capability.SuperiorShieldsCapabilityManager;
import epicsquid.superiorshields.network.PacketHandler;
import epicsquid.superiorshields.network.PacketShieldUpdate;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ItemSuperiorShield extends ItemBase implements ISuperiorShield, IBauble {

  private int ticksSinceLastRecharge = 0;

  public ItemSuperiorShield(@Nonnull String name) {
    super(name);
  }

  @Override
  public float applyShield(@Nonnull EntityPlayer player, float damage) {
    if (player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null)) {
      IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
      float absorbed = shield.getCurrentHp() - damage;
      shield.setCurrentHp(absorbed);
      resetShieldDelay(shield);
      updateClient(player, shield);
      return absorbed < 0f ? -1f * absorbed : 0f;
    }
    return damage;
  }

  @Override
  public void rechargeShield(@Nonnull IShieldCapability shield) {
    if (shield.getCurrentHp() < shield.getMaxHp()) {
      shield.setCurrentHp(shield.getCurrentHp() + 1.0f);
    }
  }

  @Override
  public void resetShieldDelay(@Nonnull IShieldCapability shield) {
    shield.setTimeWithoutDamage(0);
  }

  @Override
  public float getMaxShieldHp() {
    return 8.0f;
  }

  @Override
  public int getShieldRechargeDelay() {
    return 100;
  }

  @Override
  public int getShieldRechargeRate() {
    return 20;
  }

  @Override
  public BaubleType getBaubleType(@Nonnull ItemStack itemStack) {
    return BaubleType.BELT;
  }

  @Override
  public void onWornTick(@Nonnull ItemStack itemstack, @Nonnull EntityLivingBase player) {
    if (player instanceof EntityPlayer && player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null)) {
      if (player.world.isRemote) {
        return;
      }
      IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
      if (shield.getTimeWithoutDamage() >= getShieldRechargeDelay()) {
        if (ticksSinceLastRecharge < getShieldRechargeRate()) {
          ticksSinceLastRecharge++;
        } else {
          ticksSinceLastRecharge = 0;
          rechargeShield(shield);
          updateClient((EntityPlayer) player, shield);
        }
      } else {
        shield.setTimeWithoutDamage(shield.getTimeWithoutDamage() + 1);
      }

    }
  }

  @Override
  public void onEquipped(@Nonnull ItemStack itemstack, @Nonnull EntityLivingBase player) {
    if (player instanceof EntityPlayer && player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null)) {
      IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
      shield.setMaxHp(getMaxShieldHp());
      shield.setCurrentHp(getMaxShieldHp());
      shield.setTimeWithoutDamage(0);
    }
  }

  @Override
  public void onUnequipped(@Nonnull ItemStack itemstack, @Nonnull EntityLivingBase player) {
    if (player instanceof EntityPlayer && player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null)) {
      IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
      shield.setMaxHp(0f);
      shield.setCurrentHp(0f);
      shield.setTimeWithoutDamage(0);
    }
  }

  private void updateClient(@Nonnull EntityPlayer player, @Nonnull IShieldCapability shield) {
    if (player instanceof EntityPlayerMP) {
      PacketHandler.INSTANCE.sendTo(new PacketShieldUpdate(shield.getCurrentHp(), shield.getMaxHp()), (EntityPlayerMP) player);
    }
  }
}
