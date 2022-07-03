package epicsquid.superiorshields.shield;

public class GenericShieldType implements ShieldType {

	private final float maxHp;
	private final int shieldRechargeDelay;
	private final int shieldRechargeRate;
	private final int damage;
	private final int enchantability;

	public GenericShieldType(float maxHp, int shieldRechargeDelay, int shieldRechargeRate, int damage, int enchantability) {
		this.maxHp = maxHp;
		this.shieldRechargeDelay = shieldRechargeDelay;
		this.shieldRechargeRate = shieldRechargeRate;
		this.damage = damage;
		this.enchantability = enchantability;
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

	@Override
	public int getEnchantability() {
		return enchantability;
	}
}
