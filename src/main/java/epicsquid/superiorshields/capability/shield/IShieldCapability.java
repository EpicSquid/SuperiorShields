package epicsquid.superiorshields.capability.shield;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IShieldCapability extends INBTSerializable<CompoundTag> {

	float getCurrentHp();

	float getMaxHp();

	int getTimeWithoutDamage();

	void setCurrentHp(float currentHp);

	void setMaxHp(float maxHp);

	void setTimeWithoutDamage(int time);

	default void invalidateShield() {
		setCurrentHp(0);
		setMaxHp(0);
		setTimeWithoutDamage(0);
	}

	default void setupShield(float maxHp, float currentHp) {
		// Keep the current HP but make sure its no more than the max HP.
		// This prevents reconnecting invalidating the shield having recharged
		setMaxHp(maxHp);
		setCurrentHp(Math.min(maxHp, currentHp));
		setTimeWithoutDamage(0);
	}
}
