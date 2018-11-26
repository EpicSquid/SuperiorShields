package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.superiorshields.capability.EnergyCapabilityProvider;
import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.capability.shield.SuperiorShieldsCapabilityManager;
import epicsquid.superiorshields.shield.IEnergyShield;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class ItemEnergyShield extends ItemSuperiorShield<IEnergyShield> {

  public ItemEnergyShield(@Nonnull String name, @Nonnull IEnergyShield shieldType) {
    super(name, shieldType);
  }

  @Override
  public float applyShield(@Nonnull EntityPlayer player, @Nonnull ItemStack stack, float damage, @Nonnull DamageSource source) {
    IEnergyStorage energy = getEnergyStorage(stack);
    if (player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null) && energy != null) {
      IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
      float absorbed = shield.getCurrentHp() - damage;
      if (shield.getCurrentHp() > 0f && energy.getEnergyStored() > 200 * (damage + absorbed)) {
        energy.extractEnergy((int) (200 * (damage + absorbed)), false);
        triggerShieldEffect(player, stack, source, damage);
        return absorbDamage(player, shield, absorbed);
      }
    }
    return damage;
  }

  @Override
  public int getRGBDurabilityForDisplay(@Nonnull ItemStack stack) {
    return shieldType.getColor();
  }

  @Override
  public boolean showDurabilityBar(@Nonnull ItemStack stack) {
    return true;
  }

  @Override
  public void onUpdate(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected) {
    super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);

  }

  @Override
  public double getDurabilityForDisplay(@Nonnull ItemStack stack) {
    IEnergyStorage energy = getEnergyStorage(stack);
    NBTTagCompound tag = stack.getTagCompound();
    return MathHelper.clamp(1.0D - (tag.getInteger("energy") / (double) energy.getMaxEnergyStored()), 0.0D, 1.0D);
  }

  @Nullable
  @Override
  public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable NBTTagCompound nbt) {
    return new EnergyCapabilityProvider(shieldType.getMaxEnergy(), 0, shieldType.getMaxEnergy(), shieldType.getMaxEnergy(), stack);
  }

  @Nullable
  private IEnergyStorage getEnergyStorage(@Nonnull ItemStack stack) {
    if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
      return stack.getCapability(CapabilityEnergy.ENERGY, null);
    }
    return null;
  }
}
