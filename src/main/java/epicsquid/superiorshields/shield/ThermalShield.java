package epicsquid.superiorshields.shield;

public enum ThermalShield implements IShieldType {

  BASIC(3, 10, 40),
  REINFORCED(5, 8, 40),
  HARDENED(7, 6, 40),
  SIGNALUM(7, 4, 30),
  ENDERIUM(8, 3, 20),

  ;

  private float maxHp;
  private int shieldRechargeDelay;
  private int shieldRechargeRate;
  private int maxDamage;

  ThermalShield(float maxHp, int shieldRechargeDelay, int shieldRechargeRate) {
    this.maxHp = maxHp;
    this.shieldRechargeDelay = shieldRechargeDelay;
    this.shieldRechargeRate = shieldRechargeRate;
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

  public int getMaxDamage() {
    return 20;
  }
}
