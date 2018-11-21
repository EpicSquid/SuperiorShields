package epicsquid.superiorshields.shield;

public enum NaturesAuraShield implements IShieldType {

  INFUSED_IRON(9, 200, 40),
  SKY_INGOT(15, 200, 30),

  ;

  private int maxShieldHp;
  private int shieldRechargeDelay;
  private int shieldRechargeRate;

  NaturesAuraShield(int maxShieldHp, int shieldRechargeDelay, int shieldRechargeRate) {
    this.maxShieldHp = maxShieldHp;
    this.shieldRechargeDelay = shieldRechargeDelay;
    this.shieldRechargeRate = shieldRechargeRate;
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
