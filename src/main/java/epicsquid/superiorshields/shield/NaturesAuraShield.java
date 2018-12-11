package epicsquid.superiorshields.shield;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectKnockbackNova;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;

public enum NaturesAuraShield implements IShieldType {

  INFUSED_IRON(9, 200, 40),
  SKY_INGOT(14, 200, 30, new ShieldEffectKnockbackNova(10, 0.5f, 0, 0)),

  ;

  private int maxShieldHp;
  private int shieldRechargeDelay;
  private int shieldRechargeRate;
  private IShieldEffect effect;

  NaturesAuraShield(int maxShieldHp, int shieldRechargeDelay, int shieldRechargeRate) {
    this(maxShieldHp, shieldRechargeDelay, shieldRechargeRate, new ShieldEffectNone());
  }

  NaturesAuraShield(int maxShieldHp, int shieldRechargeDelay, int shieldRechargeRate, IShieldEffect effect) {
    this.maxShieldHp = maxShieldHp;
    this.shieldRechargeDelay = shieldRechargeDelay;
    this.shieldRechargeRate = shieldRechargeRate;
    this.effect = effect;
  }

  @Nonnull
  @Override
  public IShieldEffect getEffect() {
    return effect;
  }

  @Override
  public int getColor() {
    return 0;
  }

  @Override
  public float getMaxShieldHp() {
    return maxShieldHp;
  }

  @Override
  public int getShieldRechargeDelay() {
    return shieldRechargeDelay;
  }

  @Override
  public int getShieldRechargeRate() {
    return shieldRechargeRate;
  }
}
