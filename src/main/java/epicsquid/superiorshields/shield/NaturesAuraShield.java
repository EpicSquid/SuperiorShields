package epicsquid.superiorshields.shield;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectKnockbackNova;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;

import static epicsquid.superiorshields.config.ConfigManager.naturesAura;

public enum NaturesAuraShield implements IShieldType {

  INFUSED_IRON(naturesAura.infusedIronMaxHp, naturesAura.infusedIronRechargeDelay, naturesAura.infusedIronRechargeRate),
  SKY_INGOT(naturesAura.skyIngotMaxHp, naturesAura.skyIngotRechargeDelay, naturesAura.skyIngotRechargeRate, new ShieldEffectKnockbackNova(10, 1.0f, 1, 1)),

  ;

  private float maxShieldHp;
  private int shieldRechargeDelay;
  private int shieldRechargeRate;
  private IShieldEffect effect;

  NaturesAuraShield(float maxShieldHp, int shieldRechargeDelay, int shieldRechargeRate) {
    this(maxShieldHp, shieldRechargeDelay, shieldRechargeRate, new ShieldEffectNone());
  }

  NaturesAuraShield(float maxShieldHp, int shieldRechargeDelay, int shieldRechargeRate, IShieldEffect effect) {
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
