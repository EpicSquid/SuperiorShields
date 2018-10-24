package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.superiorshields.capability.EnergyCapabilityProvider;
import epicsquid.superiorshields.capability.IShieldCapability;
import epicsquid.superiorshields.capability.SuperiorShieldsCapabilityManager;
import epicsquid.superiorshields.shield.ThermalShield;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class ItemEnergyShield extends ItemSuperiorShield {

  public ItemEnergyShield(@Nonnull String name, @Nonnull ThermalShield shieldType) {
    super(name, shieldType);
  }

  @Override
  public float applyShield(@Nonnull EntityPlayer player, @Nonnull ItemStack stack, float damage) {
    IEnergyStorage energy = getEnergyStorage(stack);
    if (player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null) && energy != null) {
      IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
      float absorbed = shield.getCurrentHp() - damage;
      if (shield.getCurrentHp() > 0f && energy.getEnergyStored() > 100 * (damage + absorbed)) {
        shield.setCurrentHp(absorbed);
        resetShieldDelay(shield);
        energy.extractEnergy((int) (100 * (damage + absorbed)), false);
        updateClient(player, shield);
        return absorbed < 0f ? -1f * absorbed : 0f;
      }
    }
    return damage;
  }

  @Override
  public int getRGBDurabilityForDisplay(@Nonnull ItemStack stack) {
    return 0xFF0000;
  }

  @Override
  public boolean showDurabilityBar(@Nonnull ItemStack stack) {
    return getEnergyStorage(stack).getEnergyStored() > 0;
  }

  @Override
  public void onUpdate(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected) {
    super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);

  }

  @Override
  public double getDurabilityForDisplay(@Nonnull ItemStack stack) {
    IEnergyStorage energy = getEnergyStorage(stack);
    NBTTagCompound tag = stack.getTagCompound();
    return MathHelper.clamp(1.0D - ((double) tag.getInteger("energy") / (double) energy.getMaxEnergyStored()), 0.0D, 1.0D);
  }

  @Nullable
  @Override
  public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable NBTTagCompound nbt) {
    return new EnergyCapabilityProvider(10000, 0, 10000, 10000, stack);
  }

  @Nullable
  private IEnergyStorage getEnergyStorage(@Nonnull ItemStack stack) {
    if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
      return stack.getCapability(CapabilityEnergy.ENERGY, null);
    }
    return null;
  }
}
