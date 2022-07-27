package epicsquid.superiorshields.item;

import epicsquid.superiorshields.capability.EnergyCapabilityProvider;
import epicsquid.superiorshields.config.Config;
import epicsquid.superiorshields.lang.ModLang;
import epicsquid.superiorshields.shield.IEnergyShield;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

public class EnergyShieldItem extends SuperiorShieldItem<IEnergyShield> {

	public EnergyShieldItem(Item.Properties props, IEnergyShield shieldType) {
		super(props.stacksTo(1), shieldType);
	}

	@Override
	public boolean useEnergyToRecharge(@Nonnull ItemStack stack, @Nonnull Player player) {
		var energyOp = getEnergyStorage(stack);
		if (energyOp.isPresent()) {
			var energy = energyOp.get();
			int energyToConsume = Config.SHIELD.ENERGY_CONSUMPTION.get();
			if (energy.getEnergyStored() > energyToConsume) {
				energy.extractEnergy(energyToConsume, false);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isEnchantable(@Nonnull ItemStack stack) {
		return true;
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
		if (energyOp.isPresent()) {
			var energyIn = energyOp.get();
			return Math.round((float) energyIn.getEnergyStored() * 13.0F / (float) energyIn.getMaxEnergyStored());
		}
		return 0;
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable CompoundTag nbt) {
		return new EnergyCapabilityProvider(getShield().getMaxEnergy(), 0, getShield().getMaxEnergy() / 100, getShield().getMaxEnergy() / 100, stack);
	}


	@Nonnull
	private Optional<IEnergyStorage> getEnergyStorage(@Nonnull ItemStack stack) {
		return stack.getCapability(CapabilityEnergy.ENERGY).resolve();
	}


	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(@Nonnull ItemStack stack, @Nullable Level worldIn, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);

		getEnergyStorage(stack).ifPresent(energy -> {
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);

			tooltip.add(ModLang.BLANK);
			tooltip.add(new TranslatableComponent(ModLang.ENERGY.getKey(), df.format((float) energy.getEnergyStored() / 1000), df.format((float) energy.getMaxEnergyStored() / 1000)).withStyle(ChatFormatting.GRAY));
		});
	}

}
