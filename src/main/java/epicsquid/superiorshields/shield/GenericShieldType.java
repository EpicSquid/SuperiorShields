package epicsquid.superiorshields.shield;

public class GenericShieldType implements ShieldType {

	private float maxHp;
	private int shieldRechargeDelay;
	private int shieldRechargeRate;
	private int damage;

	public GenericShieldType(float maxHp, int shieldRechargeDelay, int shieldRechargeRate, int damage) {
		this.maxHp = maxHp;
		this.shieldRechargeDelay = shieldRechargeDelay;
		this.shieldRechargeRate = shieldRechargeRate;
		this.damage = damage;
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
		return 0;
	}

	@Override
	public int getMaxDamage() {
		return damage;
	}
}
