package epicsquid.superiorshields.capability;

public class ShieldCapability implements IShieldCapability {

  private int currentHp;
  private int maxHp;

  public ShieldCapability(int currentHp, int maxHp) {
    this.currentHp = currentHp;
    this.maxHp = maxHp;
  }

  @Override
  public int getCurrentHp() {
    return currentHp;
  }

  @Override
  public int getMaxHp() {
    return maxHp;
  }

  @Override
  public void setCurrentHp(int currentHp) {
    this.currentHp = currentHp;
    if (this.currentHp < 0) {
      this.currentHp = 0;
    } else if (this.currentHp > maxHp) {
      this.currentHp = maxHp;
    }
  }

  @Override
  public void setMaxHp(int maxHp) {
    this.maxHp = maxHp;
  }
}
