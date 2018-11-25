package epicsquid.superiorshields.shield;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;

public interface IShieldType {

  float getMaxShieldHp();

  int getShieldRechargeDelay();

  int getShieldRechargeRate();

  int getColor();

  @Nonnull
  default IShieldEffect getEffect() {
    return new ShieldEffectNone();
  }
}
