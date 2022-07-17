package epicsquid.superiorshields.shield;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;
import vazkii.botania.api.BotaniaAPI;

public enum BotaniaShield implements IShieldType {
	MANASTEEL(BotaniaAPI.instance().getManasteelItemTier().getEnchantmentValue(), 5f, 80, 40),
	TERRASTEEL(BotaniaAPI.instance().getTerrasteelItemTier().getEnchantmentValue(), 9f, 60, 40),
	ELEMENTIUM(BotaniaAPI.instance().getElementiumItemTier().getEnchantmentValue(), 6f, 60, 20);
	private final int enchantability;
	private final float defaultCapacity;
	private final int defaultRate;
	private final int defaultDelay;

	BotaniaShield(int enchantability, float defaultCapacity, int defaultRate, int defaultDelay) {
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
