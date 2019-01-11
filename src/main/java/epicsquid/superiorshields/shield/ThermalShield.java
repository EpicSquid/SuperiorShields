package epicsquid.superiorshields.shield;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectFireNova;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;
import epicsquid.superiorshields.shield.effect.ShieldEffectPotionNova;
import net.minecraft.init.MobEffects;

import static epicsquid.superiorshields.config.ConfigManager.thermal;

public enum ThermalShield implements IEnergyShield {

  BASIC(thermal.basicMaxHp, thermal.basicRechargeDelay, thermal.basicRechargeRate, thermal.basicEnergy),
  HARDENED(thermal.hardenedMaxHp,  thermal.hardenedRechargeDelay, thermal.hardenedRechargeRate, thermal.hardenedEnergy),
  REINFORCED(thermal.reinforcedMaxHp, thermal.reinforcedRechargeDelay, thermal.reinforcedRechargeRate, thermal.reinforcedEnergy),
  SIGNALUM(thermal.signalumMaxHp, thermal.signalumRechargeDelay, thermal.signaulmRechargeRate, thermal.signalumEnergy),
  ENDERIUM(thermal.enderiumMaxHp, thermal.enderiumRechargeDelay, thermal.enderiumRechargeRate, thermal.enderiumEnergy),

  PYROTHEUM(thermal.pyrotheumMaxHp, thermal.pyrotheumRechargeDelay, thermal.pyrotheumRechargeRate, thermal.pyrotheumEnergy, new ShieldEffectFireNova(5, 10.0)),
  CRYOTHEUM(thermal.cryotheumMaxHp, thermal.cryotheumRechargeDelay, thermal.cryotheumRechargeRate, thermal.cryotheumEnergy, new ShieldEffectPotionNova(MobEffects.SLOWNESS, 5, 10)),
  AEROTHEUM(thermal.aerotheumMaxHp, thermal.aerotheumRechargeDelay, thermal.aerotheumRechargeRate, thermal.aerotheumEnergy, new ShieldEffectPotionNova(MobEffects.BLINDNESS, 5, 10)),
  PETROTHEUM(thermal.petrotheumMaxHp, thermal.petrotheumRechargeDelay, thermal.petrotheumRechargeRate, thermal.petrotheumEnergy, new ShieldEffectPotionNova(MobEffects.WEAKNESS, 5, 10)),

  ;

  private float maxHp;
  private int shieldRechargeDelay;
  private int shieldRechargeRate;
  private int maxEnergy;
  private IShieldEffect effect;

  ThermalShield(float maxHp, int shieldRechargeDelay, int shieldRechargeRate, int maxEnergy) {
    this(maxHp, shieldRechargeDelay, shieldRechargeRate, maxEnergy, new ShieldEffectNone());
  }

  ThermalShield(float maxHp, int shieldRechargeDelay, int shieldRechargeRate, int maxEnergy, @Nonnull IShieldEffect effect) {
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
