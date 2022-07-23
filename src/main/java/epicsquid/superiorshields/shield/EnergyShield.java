package epicsquid.superiorshields.shield;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;

public enum EnergyShield implements IEnergyShield {
	ELECTRIC_SHIELD(12, 7f, 60, 60, 48000, 0x3CFE9A);

	private final int enchantability;
	private final float defaultCapacity;
	private final int defaultRate;
	private final int defaultDelay;
	private final int defaultMaxEnergy;

	private final int color;

	EnergyShield(int enchantability, float defaultCapacity, int defaultRate, int defaultDelay, int defaultMaxEnergy, int color) {
		this.enchantability = enchantability;
		this.defaultCapacity = defaultCapacity;
		this.defaultRate = defaultRate;
		this.defaultDelay = defaultDelay;
		this.defaultMaxEnergy = defaultMaxEnergy;
		this.color = color;
	}

	@Override
	public int getColor() {
		return color;
	}

	@Override
	public int getEnchantability() {
		return enchantability;
	}

	@Override
	public IShieldEffect getEffect() {
		return new ShieldEffectNone();
	}

	@Override
	public float getDefaultCapacity() {
		return defaultCapacity;
	}

	@Override
	public int getDefaultRate() {
		return defaultRate;
	}

	@Override
	public int getDefaultDelay() {
		return defaultDelay;
	}


	@Override
	public int getMaxEnergy() {
		return defaultMaxEnergy;
	}
}
