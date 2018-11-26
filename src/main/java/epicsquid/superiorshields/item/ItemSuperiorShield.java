package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.capability.shield.SuperiorShieldsCapabilityManager;
import epicsquid.superiorshields.event.ShieldEquippedEvent;
import epicsquid.superiorshields.network.PacketHandler;
import epicsquid.superiorshields.network.PacketShieldUpdate;
import epicsquid.superiorshields.shield.IShieldType;
import epicsquid.superiorshields.shield.effect.EffectTrigger;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemSuperiorShield<T extends IShieldType> extends ItemBase implements ISuperiorShield, IBauble {

  private int ticksSinceLastRecharge = 0;
  protected T shieldType;

  public ItemSuperiorShield(@Nonnull String name, @Nonnull T shieldType) {
    super(name);
    this.shieldType = shieldType;
  }

  @Override
  public float applyShield(@Nonnull EntityPlayer player, @Nonnull ItemStack stack, float damage, @Nonnull DamageSource source) {
    if (player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null)) {
      IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
      triggerShieldEffect(player, stack, source, damage, EffectTrigger.DAMAGE);
      return absorbDamage(player, shield, shield.getCurrentHp() - damage);
    }
    return damage;
  }

  protected float absorbDamage(@Nonnull EntityPlayer player, @Nonnull IShieldCapability shield, float absorbed) {
    shield.setCurrentHp(absorbed);
    resetShieldDelay(shield);
    updateClient(player, shield);
    return absorbed < 0f ? -1f * absorbed : 0f;
  }

  @Override
  public void rechargeShield(@Nonnull IShieldCapability shield, @Nonnull ItemStack stack, @Nonnull EntityPlayer player) {
    if (shield.getCurrentHp() < shield.getMaxHp()) {
      if (useEnergyToRecharge(stack, player)) {
        shield.setCurrentHp(shield.getCurrentHp() + 1.0f);
      }
    }
  }

  /**
   * Triggers the use of energy to recharge.
   * @param stack The stack to get the capability to recharge from.
   * @return true if there was enough energy to recharge.
   */
  protected boolean useEnergyToRecharge(@Nonnull ItemStack stack, @Nonnull EntityPlayer player) {
    return true;
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
  public void onWornTick(@Nonnull ItemStack stack, @Nonnull EntityLivingBase player) {
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
          rechargeShield(shield, stack, (EntityPlayer) player);
          updateClient((EntityPlayer) player, shield);
          triggerShieldEffect((EntityPlayer) player, stack, null, 0f, EffectTrigger.RECHARGE);
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
      shield.setCurrentHp(0);
      shield.setTimeWithoutDamage(0);
      MinecraftForge.EVENT_BUS.post(new ShieldEquippedEvent((EntityPlayer) player, shield));
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

  @Override
  @SideOnly(Side.CLIENT)
  public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, ITooltipFlag flagIn) {
    tooltip.add(I18n.format("superiorshields.tooltip.hp") + " " + shieldType.getMaxShieldHp() + " " + I18n.format("superiorshields.tooltip.hpDetail"));
    tooltip.add(I18n.format("superiorshields.tooltip.rechargeDelay") + " " + (float) shieldType.getShieldRechargeDelay() / 20 + " " + I18n
        .format("superiorshields.tooltip.rechargeDelayTime"));
    tooltip.add(I18n.format("superiorshields.tooltip.rechargeRate") + " " + 1f / ((float) shieldType.getShieldRechargeRate() / 20) + " " + I18n
        .format("superiorshields.tooltip.rechargeRateTime"));
  }

  protected void updateClient(@Nonnull EntityPlayer player, @Nonnull IShieldCapability shield) {
    if (player instanceof EntityPlayerMP) {
      PacketHandler.INSTANCE.sendTo(new PacketShieldUpdate(shield.getCurrentHp(), shield.getMaxHp()), (EntityPlayerMP) player);
    }
  }

  public void triggerShieldEffect(@Nonnull EntityPlayer player, @Nonnull ItemStack stack, @Nullable DamageSource source, float damage, EffectTrigger trigger) {
    if (player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null)) {
      IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
      shieldType.getEffect().applyEffect(shield, player, source, damage, trigger);
    }
  }
}
