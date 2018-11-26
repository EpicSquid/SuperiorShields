package epicsquid.superiorshields.capability.shield;

public interface IShieldCapability {

  float getCurrentHp();

  float getMaxHp();

  int getTimeWithoutDamage();

  void setCurrentHp(float currentHp);

  void setMaxHp(float maxHp);

  void setTimeWithoutDamage(int time);
}
