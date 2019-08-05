package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.superiorshields.capability.EnergyCapabilityProvider;
import epicsquid.superiorshields.shield.EnergyShield;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class EnergyShieldItem extends SuperiorShieldItem<EnergyShield> {

	private int energyToConsume = 400;

	public EnergyShieldItem(Item.Properties props, EnergyShield shieldType) {
		super(props, shieldType);
	}

	@Override
	protected boolean useEnergyToRecharge(@Nonnull ItemStack stack, @Nonnull PlayerEntity player) {
		IEnergyStorage energy = getEnergyStorage(stack);
		if (energy != null && energy.getEnergyStored() > energyToConsume) {
			energy.extractEnergy(energyToConsume, false);
			return true;
		}
		return false;
	}

	@Override
	public int getRGBDurabilityForDisplay(@Nonnull ItemStack stack) {
		return getShield().getColor();
	}

	@Override
	public boolean showDurabilityBar(@Nonnull ItemStack stack) {
		return true;
	}

	@Override
	public double getDurabilityForDisplay(@Nonnull ItemStack stack) {
		IEnergyStorage energy = getEnergyStorage(stack);
		CompoundNBT tag = stack.getTag();
		return MathHelper.clamp(1.0D - (tag.getInt("energy") / (double) energy.getMaxEnergyStored()), 0.0D, 1.0D);
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable CompoundNBT nbt) {
		return new EnergyCapabilityProvider(getShield().getMaxEnergy(), 0, getShield().getMaxEnergy(), getShield().getMaxEnergy(), stack);
	}

	@Nullable
	private IEnergyStorage getEnergyStorage(@Nonnull ItemStack stack) {
		return stack.getCapability(CapabilityEnergy.ENERGY).orElse(null);
	}
}
