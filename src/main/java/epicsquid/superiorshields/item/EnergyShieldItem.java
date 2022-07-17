package epicsquid.superiorshields.item;

import epicsquid.superiorshields.capability.EnergyCapabilityProvider;
import epicsquid.superiorshields.shield.IEnergyShield;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class EnergyShieldItem extends SuperiorShieldItem<IEnergyShield> {

	private int energyToConsume = 400;

	public EnergyShieldItem(Item.Properties props, IEnergyShield shieldType) {
		super(props, shieldType);
	}

	@Override
	public boolean useEnergyToRecharge(@Nonnull ItemStack stack, @Nonnull Player player) {
		var energyOp = getEnergyStorage(stack);
		if (energyOp.isPresent()) {
			var energy = energyOp.get();
			if (energy.getEnergyStored() > energyToConsume) {
				energy.extractEnergy(energyToConsume, false);
				return true;
			}
		}
		return false;
	}

	@Override
	public int getBarColor(@Nonnull ItemStack stack) {
		return getShield().getColor();
	}


	@Override
	public boolean isBarVisible(@Nonnull ItemStack stack) {
		return true;
	}


	@Override
	public int getBarWidth(@Nonnull ItemStack stack) {
		var energyOp = getEnergyStorage(stack);
		CompoundTag tag = stack.getTag();
		if (tag != null && energyOp.isPresent()) {
			var energy = energyOp.get();
			return Math.round(13 * (tag.getInt("energy") / (float) energy.getMaxEnergyStored()));
		}
		return 0;
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable CompoundTag nbt) {
		return new EnergyCapabilityProvider(getShield().getMaxEnergy(), 0, getShield().getMaxEnergy(), getShield().getMaxEnergy(), stack);
	}

	@Nonnull
	private Optional<IEnergyStorage> getEnergyStorage(@Nonnull ItemStack stack) {
		return stack.getCapability(CapabilityEnergy.ENERGY).resolve();
	}
}
