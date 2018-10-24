package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.superiorshields.capability.IShieldCapability;
import epicsquid.superiorshields.capability.SuperiorShieldsCapabilityManager;
import epicsquid.superiorshields.network.PacketHandler;
import epicsquid.superiorshields.network.PacketShieldUpdate;
import epicsquid.superiorshields.shield.IShieldType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ItemSuperiorShield extends ItemBase implements ISuperiorShield, IBauble {

  private int ticksSinceLastRecharge = 0;
  protected IShieldType shieldType;

  public ItemSuperiorShield(@Nonnull String name, @Nonnull IShieldType shieldType) {
    super(name);
    this.shieldType = shieldType;
  }

  @Override
  public float applyShield(@Nonnull EntityPlayer player, @Nonnull ItemStack stack, float damage) {
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
      if (shield.getTimeWithoutDamage() >= shieldType.getShieldRechargeDelay()) {
        if (ticksSinceLastRecharge < shieldType.getShieldRechargeRate()) {
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
      shield.setMaxHp(shieldType.getMaxShieldHp());
      shield.setCurrentHp(shieldType.getMaxShieldHp());
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

  protected void updateClient(@Nonnull EntityPlayer player, @Nonnull IShieldCapability shield) {
    if (player instanceof EntityPlayerMP) {
      PacketHandler.INSTANCE.sendTo(new PacketShieldUpdate(shield.getCurrentHp(), shield.getMaxHp()), (EntityPlayerMP) player);
    }
  }
}
