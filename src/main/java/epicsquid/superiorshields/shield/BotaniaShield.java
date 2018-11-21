package epicsquid.superiorshields.shield;

public enum BotaniaShield implements IShieldType {

  MANA_STEEL(4, 40, 15),
  TERRA_STEEL(7, 30, 10),
  ELEMENTIUM(5, 15, 10),

  ;

  private int maxShieldHp;
  private int rechargeDelay;
  private int rechargeRate;

  BotaniaShield(int maxShieldHp, int rechargeDelay, int rechargeRate) {
    this.maxShieldHp = maxShieldHp;
    this.rechargeDelay = rechargeDelay;
    this.rechargeRate = rechargeRate;
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
