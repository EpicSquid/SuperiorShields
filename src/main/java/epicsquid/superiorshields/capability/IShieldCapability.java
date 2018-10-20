package epicsquid.superiorshields.capability;

public interface IShieldCapability {

  float getCurrentHp();

  float getMaxHp();

  void setCurrentHp(float currentHp);

  void setMaxHp(float maxHp);
}
