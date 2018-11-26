package epicsquid.superiorshields.shield;

public enum EnderIOShield implements IEnergyShield {

  DS_CONDUCTIVE_IRON(3, 40, 10, 60000),
  DS_ENERGETIC_ALLOY(5, 40, 10, 120000),
  DS_VIBRANT_ALLOY(8, 30, 10, 240000),
  DS_ENERGETIC_SILVER(5, 40, 10, 120000),
  DS_VIVID_ALLOY(8, 30, 10, 240000),
  DS_PULSATING_IRON(4, 30, 5, 80000),
  DS_REDSTONE_ALLOY(4, 20, 10, 80000),
  DS_CRYSTALLINE_ALLOY(10, 30, 10, 5120000),
  DS_MELODIC_ALLOY(12, 25, 10, 10240000),

  ES_CONDUCTIVE_IRON(5, 40, 10, 120000),
  ES_ENERGETIC_ALLOY(7, 40, 10, 240000),
  ES_VIBRANT_ALLOY(10, 30, 10, 480000),
  ES_ENERGETIC_SILVER(7, 40, 10, 240000),
  ES_VIVID_ALLOY(10, 30, 10, 480000),
  ES_PULSATING_IRON(6, 30, 5, 160000),
  ES_REDSTONE_ALLOY(6, 40, 10, 160000),
  ES_CRYSTALLINE_ALLOY(12, 30, 10, 10240000),
  ES_MELODIC_ALLOY(15, 25, 10, 20480000),
  STELLAR_ALLOY(20, 20, 10, 40960000),

  SOULARIUM(10, 200, 40, 480000)

  ;

  private float maxHp;
  private int shieldRechargeDelay;
  private int shieldRechargeRate;
  private int maxEnergy;

  EnderIOShield(float maxHp, int shieldRechargeDelay, int shieldRechargeRate, int maxEnergy) {
    this.maxHp = maxHp;
    this.shieldRechargeDelay = shieldRechargeDelay;
    this.shieldRechargeRate = shieldRechargeRate;
    this.maxEnergy = maxEnergy;
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
