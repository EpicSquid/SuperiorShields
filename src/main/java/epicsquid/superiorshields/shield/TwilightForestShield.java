package epicsquid.superiorshields.shield;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;

public enum TwilightForestShield implements IShieldType {
	// Fast iron, gold + iron
	IRONWOOD(25, 5f, 40, 40),
	// Tough, slow shield
	KNIGHTMETAL(8, 9f, 60, 100),
	STEELLEAF(9, 6f, 40, 40);

	private final int enchantability;
	private final float defaultCapacity;
	private final int defaultRate;
	private final int defaultDelay;

	TwilightForestShield(int enchantability, float defaultCapacity, int defaultRate, int defaultDelay) {
		this.enchantability = enchantability;
		this.defaultCapacity = defaultCapacity;
		this.defaultRate = defaultRate;
		this.defaultDelay = defaultDelay;
	}

	@Override
	public int getColor() {
		return 0;
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
}
