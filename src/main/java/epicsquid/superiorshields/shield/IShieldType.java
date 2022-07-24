package epicsquid.superiorshields.shield;

import epicsquid.superiorshields.config.Config;
import epicsquid.superiorshields.config.IShieldConfig;
import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;

public interface IShieldType {

	default IShieldConfig getConfig() {
		return Config.SHIELD.SHIELDS.get(this);
	}

	default float getCapacity() {
		return getConfig().getCapacity();
	}

	default int getDelay() {
		return getConfig().getDelay();
	}

	default int getRate() {
		return getConfig().getRate();
	}

	default int getColor() {
		return 0;
	}

	default IShieldEffect getEffect() {
		return new ShieldEffectNone();
	}

	String name();

	float getDefaultCapacity();

	int getDefaultRate();

	int getDefaultDelay();

	int getEnchantability();
}
