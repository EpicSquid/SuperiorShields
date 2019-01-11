package epicsquid.superiorshields.shield;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;
import epicsquid.superiorshields.shield.effect.ShieldEffectPotionNova;
import net.minecraft.init.MobEffects;

import static epicsquid.superiorshields.config.ConfigManager.enderio;
import static epicsquid.superiorshields.config.ConfigManager.endergy;

public enum EnderIOShield implements IEnergyShield {

  DS_CONDUCTIVE_IRON(enderio.darkSteelConductiveIronMaxHp, enderio.darkSteelConductiveIronRechargeDelay, enderio.darkSteelConductiveIronRechargeRate, enderio.darkSteelConductiveIronEnergy),
  DS_ENERGETIC_ALLOY(enderio.darkSteelEnergeticAlloyMaxHp, enderio.darkSteelEnergeticAlloyRechargeDelay, enderio.darkSteelEnergeticAlloyRechargeRate, enderio.darkSteelEnergeticAlloyEnergy),
  DS_VIBRANT_ALLOY(enderio.darkSteelVibrantAlloyMaxHp, enderio.darkSteelVibrantAlloyRechargeDelay, enderio.darkSteelVibrantAlloyRechargeRate, enderio.darkSteelVibrantAlloyEnergy),
  DS_ENERGETIC_SILVER(endergy.darkSteelEnergeticSilverMaxHp, endergy.darkSteelEnergeticSilverRechargeDelay, endergy.darkSteelEnergeticSilverRechargeRate, endergy.darkSteelEnergeticSilverEnergy),
  DS_VIVID_ALLOY(endergy.darkSteelVividAlloyMaxHp, endergy.darkSteelVividAlloyRechargeDelay, endergy.darkSteelVividAlloyRechargeRate, endergy.darkSteelVividAlloyEnergy),
  DS_PULSATING_IRON(4, 30, 5, 80000),
  DS_REDSTONE_ALLOY(4, 20, 10, 80000),
  DS_CRYSTALLINE_ALLOY(endergy.darkSteelCrystallineAlloyMaxHp, endergy.darkSteelCrystallineAlloyRechargeDelay, endergy.darkSteelCrystallineAlloyRechargeRate, endergy.darkSteelCrystallineAlloyEnergy),
  DS_MELODIC_ALLOY(endergy.darkSteelMelodicAlloyMaxHp, endergy.darkSteelMelodicAlloyRechargeDelay, endergy.darkSteelMelodicAlloyRechargeRate, endergy.darkSteelMelodicAlloyEnergy),

  ES_CONDUCTIVE_IRON(enderio.endSteelConductiveIronMaxHp, enderio.endSteelConductiveIronRechargeDelay, enderio.endSteelConductiveIronRechargeRate, enderio.endSteelConductiveIronEnergy),
  ES_ENERGETIC_ALLOY(enderio.endSteelEnergeticAlloyMaxHp, enderio.endSteelEnergeticAlloyRechargeDelay, enderio.endSteelEnergeticAlloyRechargeRate, enderio.endSteelEnergeticAlloyEnergy),
  ES_VIBRANT_ALLOY(enderio.endSteelVibrantAlloyMaxHp, enderio.endSteelVibrantAlloyRechargeDelay, enderio.endSteelVibrantAlloyRechargeRate, enderio.endSteelVibrantAlloyEnergy),
  ES_ENERGETIC_SILVER(endergy.endSteelEnergeticSilverMaxHp, endergy.endSteelEnergeticSilverRechargeDelay, endergy.endSteelEnergeticSilverRechargeRate, endergy.endSteelEnergeticSilverEnergy),
  ES_VIVID_ALLOY(endergy.endSteelVividAlloyMaxHp, endergy.endSteelVividAlloyRechargeDelay, endergy.endSteelVividAlloyRechargeRate, endergy.endSteelVividAlloyEnergy),
  ES_PULSATING_IRON(6, 30, 5, 160000),
  ES_REDSTONE_ALLOY(6, 40, 10, 160000),
  ES_CRYSTALLINE_ALLOY(endergy.endSteelCrystallineAlloyMaxHp, endergy.endSteelCrystallineAlloyRechargeDelay, endergy.endSteelCrystallineAlloyRechargeRate, endergy.endSteelCrystallineAlloyEnergy),
  ES_MELODIC_ALLOY(endergy.endSteelMelodicAlloyMaxHp, endergy.endSteelMelodicAlloyRechargeDelay, endergy.endSteelMelodicAlloyRechargeRate, endergy.endSteelMelodicAlloyEnergy),
  STELLAR_ALLOY(endergy.stellarAlloyMaxHp, endergy.stellarAlloyRechargeDelay, endergy.stellarAlloyRechargeRate, endergy.stellarAlloyEnergy),

  SOULARIUM(enderio.soulariumMaxHp, enderio.soulariumRechargeDelay, enderio.soulariumRechargeRate, enderio.soulariumEnergy, new ShieldEffectPotionNova(MobEffects.LEVITATION, 100, 10.0))

  ;

  private float maxHp;
  private int shieldRechargeDelay;
  private int shieldRechargeRate;
  private int maxEnergy;
  private IShieldEffect effect;

  EnderIOShield(float maxHp, int shieldRechargeDelay, int shieldRechargeRate, int maxEnergy) {
    this(maxHp, shieldRechargeDelay, shieldRechargeRate, maxEnergy, new ShieldEffectNone());
  }

  EnderIOShield(float maxHp, int shieldRechargeDelay, int shieldRechargeRate, int maxEnergy, IShieldEffect effect) {
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
    return 0xB96DED;
  }

  @Override
  public int getMaxEnergy() {
    return maxEnergy;
  }
}
