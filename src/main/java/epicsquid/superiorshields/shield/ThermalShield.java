package epicsquid.superiorshields.shield;

public enum ThermalShield implements IShieldType {

  BASIC(3, 12, 40, 40000),
  REINFORCED(4, 10, 40, 120000),
  HARDENED(5, 8, 40, 240000),
  SIGNALUM(6, 6, 30, 400000),
  ENDERIUM(7, 4, 20, 600000),

  ;

  private float maxHp;
  private int shieldRechargeDelay;
  private int shieldRechargeRate;
  private int maxEnergy;

  ThermalShield(float maxHp, int shieldRechargeDelay, int shieldRechargeRate, int maxEnergy) {
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
    return 0xFF0000;
  }

  @Override
  public int getMaxEnergy() {
    return maxEnergy;
  }
}
