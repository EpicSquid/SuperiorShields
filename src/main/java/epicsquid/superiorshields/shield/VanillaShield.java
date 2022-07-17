package epicsquid.superiorshields.shield;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;
import net.minecraft.world.item.Tiers;

public enum VanillaShield implements IShieldType {
	IRON(Tiers.IRON.getEnchantmentValue(), 5f, 80, 40),
	GOLD(Tiers.GOLD.getEnchantmentValue(), 3f, 20, 40),
	DIAMOND(Tiers.DIAMOND.getEnchantmentValue(), 7f, 60, 40),
	NETHERITE(Tiers.NETHERITE.getEnchantmentValue(), 8f, 40, 40);

	private final int enchantability;
	private final float defaultCapacity;
	private final int defaultRate;
	private final int defaultDelay;

	VanillaShield(int enchantability, float defaultCapacity, int defaultRate, int defaultDelay) {
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
