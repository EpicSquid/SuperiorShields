package epicsquid.superiorshields.capability;

public interface IShieldCapability {

  int getCurrentHp();

  int getMaxHp();

  void setCurrentHp(int currentHp);

  void setMaxHp(int maxHp);
}
