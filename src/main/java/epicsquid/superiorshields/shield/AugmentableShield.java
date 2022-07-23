package epicsquid.superiorshields.shield;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;

public enum AugmentableShield implements IEnergyShield {
	BASE(5f, 80, 40, 50000),
	HARDENED(7f, 80, 40, 100000),
	REINFORCED(8f, 60, 40, 150000),
	RESONANT(10f, 40, 40, 200000);
	private final float defaultCapacity;
	private final int defaultRate;
	private final int defaultDelay;
	private final int defaultMaxEnergy;

	AugmentableShield(float defaultCapacity, int defaultRate, int defaultDelay, int defaultMaxEnergy) {
		this.defaultCapacity = defaultCapacity;
		this.defaultRate = defaultRate;
		this.defaultDelay = defaultDelay;
		this.defaultMaxEnergy = defaultMaxEnergy;
	}

	@Override
	public int getColor() {
		return 0xFF0000;
	}

	@Override
	public int getEnchantability() {
		return 8;
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
