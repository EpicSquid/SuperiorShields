package epicsquid.superiorshields.shield;

import epicsquid.superiorshields.config.Config;
import epicsquid.superiorshields.config.ShieldsConfig;
import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;

public interface ShieldType {

	String getConfigName();

	default float getMaxShieldHp() {
		return Config.SHIELD.SHIELDS.get(getConfigName()).getCapacity();
	}

	default int getShieldRechargeDelay() {
		return Config.SHIELD.SHIELDS.get(getConfigName()).getDelay();
	};

	default int getShieldRechargeRate() {
		return Config.SHIELD.SHIELDS.get(getConfigName()).getRate();
	}

	int getColor();

	default IShieldEffect getEffect() {
		return new ShieldEffectNone();
	}

	int getEnchantability();
}
