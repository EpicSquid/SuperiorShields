package epicsquid.superiorshields.capability;

public class ShieldCapability implements IShieldCapability {

  private float currentHp;
  private float maxHp;

  public ShieldCapability(float currentHp, float maxHp) {
    this.currentHp = currentHp;
    this.maxHp = maxHp;
  }

  @Override
  public float getCurrentHp() {
    return currentHp;
  }

  @Override
  public float getMaxHp() {
    return maxHp;
  }

  @Override
  public void setCurrentHp(float currentHp) {
    this.currentHp = currentHp;
    if (this.currentHp < 0) {
      this.currentHp = 0;
    } else if (this.currentHp > maxHp) {
      this.currentHp = maxHp;
    }
  }

  @Override
  public void setMaxHp(float maxHp) {
    this.maxHp = maxHp;
  }
}
