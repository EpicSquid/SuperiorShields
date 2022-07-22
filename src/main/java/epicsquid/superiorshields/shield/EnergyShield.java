package epicsquid.superiorshields.shield;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;
import net.minecraft.world.item.Tiers;

public enum EnergyShield implements IEnergyShield {
	ENERGY(12, 8f, 20, 20, 20000);

	private final int enchantability;
	private final float defaultCapacity;
	private final int defaultRate;
	private final int defaultDelay;
	private final int defaultMaxEnergy;

	EnergyShield(int enchantability, float defaultCapacity, int defaultRate, int defaultDelay, int defaultMaxEnergy) {
		this.enchantability = enchantability;
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
