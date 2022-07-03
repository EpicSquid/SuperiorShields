package epicsquid.superiorshields.capability.shield;

import net.minecraft.nbt.CompoundTag;

public class ShieldCapability implements IShieldCapability {

	private static final String NBT_MAX_SHIELD_HP = "maxShieldHp";
	private static final String NBT_SHIELD_HP = "shieldHp";
	private static final String NBT_TIME_WITHOUT_DAMAGE = "timeWithoutDamage";

	private float currentHp;
	private float maxHp;
	private int timeWithoutDamage;

	public ShieldCapability() {

	}

	public ShieldCapability(float currentHp, float maxHp, int timeWithoutDamage) {
		this.currentHp = currentHp;
		this.maxHp = maxHp;
		this.timeWithoutDamage = timeWithoutDamage;
	}

	@Override
	public float getCurrentHp() {
		return currentHp;
	}

	@Override
	public float getMaxHp() {
		return maxHp;
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

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag tag = new CompoundTag();
		tag.putFloat(NBT_MAX_SHIELD_HP, getMaxHp());
		tag.putFloat(NBT_SHIELD_HP, getCurrentHp());
		tag.putInt(NBT_TIME_WITHOUT_DAMAGE, getTimeWithoutDamage());
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundTag tag) {
		setMaxHp(tag.getFloat(NBT_MAX_SHIELD_HP));
		setCurrentHp(tag.getFloat(NBT_SHIELD_HP));
		setTimeWithoutDamage(tag.getInt(NBT_TIME_WITHOUT_DAMAGE));
	}
}
