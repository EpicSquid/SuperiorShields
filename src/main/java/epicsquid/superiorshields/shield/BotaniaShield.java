package epicsquid.superiorshields.shield;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.shield.effect.EffectTrigger;
import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;
import epicsquid.superiorshields.shield.effect.ShieldEffectPotion;
import net.minecraft.init.MobEffects;

public enum BotaniaShield implements IShieldType {

  MANA_STEEL(4, 40, 15),
  TERRA_STEEL(7, 30, 10, new ShieldEffectPotion(MobEffects.REGENERATION, EffectTrigger.FILLED, 100)),
  ELEMENTIUM(5, 15, 10, new ShieldEffectPotion(MobEffects.HASTE, EffectTrigger.FULL, 70)),

  ;

  private int maxShieldHp;
  private int rechargeDelay;
  private int rechargeRate;
  private IShieldEffect effect;

  BotaniaShield(int maxShieldHp, int rechargeDelay, int rechargeRate, IShieldEffect effect) {
    this.maxShieldHp = maxShieldHp;
    this.rechargeDelay = rechargeDelay;
    this.rechargeRate = rechargeRate;
    this.effect = effect;
  }

  BotaniaShield(int maxShieldHp, int rechargeDelay, int rechargeRate) {
    this(maxShieldHp, rechargeDelay, rechargeRate, new ShieldEffectNone());
  }

  @Nonnull
  @Override
  public IShieldEffect getEffect() {
    return effect;
  }

  @Override
  public float getMaxShieldHp() {
    return maxShieldHp;
  }

  @Override
  public int getShieldRechargeDelay() {
    return rechargeDelay;
  }

  @Override
  public int getShieldRechargeRate() {
    return rechargeRate;
  }

  @Override
  public int getColor() {
    return 0;
  }
}
