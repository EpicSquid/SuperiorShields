package epicsquid.superiorshields.shield;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;

public enum MetalShield implements IShieldType {
	// Early game fast shield, compare to copper
	TIN(18, 3f, 40, 40),
	// Mid game tank shield, compare to iron
	LEAD(16, 7f, 40, 80),
	// Mid game fast shield, compare to iron/gold
	SILVER(30, 4f, 40, 20),
	// Mid game faster shield, compare to iron
	NICKEL(30, 5f, 40, 40),
	// Mid game balanced shield, compare to iron
	BRONZE(16, 7f, 80, 40),
	// Later game fastest shield, compare to silver/gold
	ELECTRUM(28, 6f, 20, 10),
	// Later game balanced shield, compare to iron/nickel
	INVAR(13, 8f, 40, 40),
	// Mid game balanced shield, compare to iron/copper
	CONSTANTAN(10, 5f, 60, 20);

	private final int enchantability;
	private final float defaultCapacity;
	private final int defaultRate;
	private final int defaultDelay;

	MetalShield(int enchantability, float defaultCapacity, int defaultRate, int defaultDelay) {
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
