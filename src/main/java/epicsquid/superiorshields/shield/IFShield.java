package epicsquid.superiorshields.shield;

import javax.annotation.Nonnull;

import com.buuz135.industrial.entity.EntityPinkSlime;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectFood;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;
import epicsquid.superiorshields.shield.effect.ShieldEffectSpawn;

import static epicsquid.superiorshields.config.ConfigManager.industrialForegoing;

public enum IFShield implements IEnergyShield {

  PINK_SLIME(industrialForegoing.pinkSlimeMaxHp, industrialForegoing.pinkSlimeRechargeDelay, industrialForegoing.pinkSlimeRechargeRate, industrialForegoing.pinkSlimeEnergy, new ShieldEffectSpawn<>(EntityPinkSlime.class, 0.03f, "shield.effect.spawn.pinkslime")),
  MEAT(industrialForegoing.meatMaxHp, industrialForegoing.meatRechargeDelay, industrialForegoing.meatRechargeRate, industrialForegoing.meatEnergy, new ShieldEffectFood(0.05f, 1, 1.0f)),

  ;

  private float maxHp;
  private int shieldRechargeDelay;
  private int shieldRechargeRate;
  private int maxEnergy;
  private IShieldEffect effect;

  IFShield(float maxHp, int shieldRechargeDelay, int shieldRechargeRate, int maxEnergy) {
    this(maxHp, shieldRechargeDelay, shieldRechargeRate, maxEnergy, new ShieldEffectNone());
  }

  IFShield(float maxHp, int shieldRechargeDelay, int shieldRechargeRate, int maxEnergy, @Nonnull IShieldEffect effect) {
    this.maxHp = maxHp;
    this.shieldRechargeDelay = shieldRechargeDelay;
    this.shieldRechargeRate = shieldRechargeRate;
    this.maxEnergy = maxEnergy;
    this.effect = effect;
  }

  @Nonnull
  @Override
  public IShieldEffect getEffect() {
    return effect;
  }

  @Override
  public float getMaxShieldHp() {
    return maxHp;
  }

  @Override
  public int getShieldRechargeDelay() {
    return shieldRechargeDelay;
  }

  @Override
  public int getShieldRechargeRate() {
    return shieldRechargeRate;
  }

  @Override
  public int getColor() {
    return 0xFF0000;
  }

  @Override
  public int getMaxEnergy() {
    return maxEnergy;
  }
}
