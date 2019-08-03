package epicsquid.superiorshields.capability.shield;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ShieldCapabilityProvider implements ICapabilityProvider, INBTSerializable, IShieldCapability {

	private float currentHp;
	private float maxHp;
	private int timeWithoutDamage;
	private LazyOptional<ShieldCapabilityProvider> op;

	public ShieldCapabilityProvider(boolean isNewShield) {
		if (isNewShield) {
			currentHp = 0;
			maxHp = 0;
			timeWithoutDamage = 0;
		}
		op = LazyOptional.of(() -> this);
	}

	@Nullable
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
		return getCapability(capability);
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
		return cap == SuperiorShieldsCapabilityManager.shieldCapability ? (LazyOptional<T>) op : LazyOptional.empty();
	}

	@Override
	public INBT serializeNBT() {
		CompoundNBT tag = new CompoundNBT();
		tag.putFloat(ShieldCapabilityStorage.NBT_MAX_SHIELD_HP, getMaxHp());
		tag.putFloat(ShieldCapabilityStorage.NBT_SHIELD_HP, getCurrentHp());
		tag.putInt(ShieldCapabilityStorage.NBT_TIME_WITHOUT_DAMAGE, getTimeWithoutDamage());
		return tag;
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		if (nbt instanceof CompoundNBT) {
			CompoundNBT tag = (CompoundNBT) nbt;
			if (tag.contains(ShieldCapabilityStorage.NBT_MAX_SHIELD_HP)) {
				setMaxHp(tag.getFloat(ShieldCapabilityStorage.NBT_MAX_SHIELD_HP));
			}
			if (tag.contains(ShieldCapabilityStorage.NBT_SHIELD_HP)) {
				setCurrentHp(tag.getFloat(ShieldCapabilityStorage.NBT_SHIELD_HP));
			}
			if (tag.contains(ShieldCapabilityStorage.NBT_TIME_WITHOUT_DAMAGE)) {
				setTimeWithoutDamage(tag.getInt(ShieldCapabilityStorage.NBT_TIME_WITHOUT_DAMAGE));
			}
		}
	}

	@Override
	public float getMaxHp() {
		return maxHp;
	}

	@Override
	public float getCurrentHp() {
		return currentHp;
	}

	@Override
	public int getTimeWithoutDamage() {
		return timeWithoutDamage;
	}

	@Override
	public void setCurrentHp(float currentHp) {
		this.currentHp = currentHp;
		if (this.currentHp < 0) {
			this.currentHp = 0;
		} else if (this.currentHp > maxHp) {
			this.currentHp = maxHp;
		}
	}

	@Override
	public void setMaxHp(float maxHp) {
		this.maxHp = maxHp;
	}

	@Override
	public void setTimeWithoutDamage(int timeWithoutDamage) {
		this.timeWithoutDamage = timeWithoutDamage;
	}
}
