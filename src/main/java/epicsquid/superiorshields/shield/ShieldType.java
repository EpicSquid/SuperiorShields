package epicsquid.superiorshields.shield;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;

public interface ShieldType {

	float getMaxShieldHp();

	int getShieldRechargeDelay();

	int getShieldRechargeRate();

	int getColor();

	default IShieldEffect getEffect() {
		return new ShieldEffectNone();
	}

	int getEnchantability();
}
