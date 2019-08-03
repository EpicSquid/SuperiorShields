package epicsquid.superiorshields.item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.superiorshields.capability.EnergyCapabilityProvider;
import epicsquid.superiorshields.shield.IEnergyShield;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class ItemEnergyShield extends ItemSuperiorShield<IEnergyShield> {

	private int energyToConsume = 400;

	public ItemEnergyShield(Item.Properties props, IEnergyShield shieldType) {
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
		return shieldType.getColor();
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
		return new EnergyCapabilityProvider(shieldType.getMaxEnergy(), 0, shieldType.getMaxEnergy(), shieldType.getMaxEnergy(), stack);
	}

	@Nullable
	private IEnergyStorage getEnergyStorage(@Nonnull ItemStack stack) {
		return stack.getCapability(CapabilityEnergy.ENERGY).orElse(null);
	}
}
