package epicsquid.superiorshields.shield;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;

public enum MekanismMetalShield implements IShieldType {
	// Mid game fast shield, compare to gold
	LAPIS(32, 3f, 20, 40),
	// Slightly different to Iron
	OSMIUM(14, 4f, 60, 40),
	// Mid game, tough shield
	STEEL(16, 8f, 80, 100),
	// Later game shield, better than osmium
	REFINED_GLOWSTONE(20, 6f, 20, 60),
	// Later game shield, better than osmium
	REFINED_OBSIDIAN(18, 12f, 60, 40),
	// Mid game balanced shield, compare to iron/copper
	CONSTANTAN(10, 6f, 60, 10);

	private final int enchantability;
	private final float defaultCapacity;
	private final int defaultRate;
	private final int defaultDelay;

	MekanismMetalShield(int enchantability, float defaultCapacity, int defaultRate, int defaultDelay) {
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
