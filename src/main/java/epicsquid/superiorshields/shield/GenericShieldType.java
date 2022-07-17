package epicsquid.superiorshields.shield;

public class GenericShieldType implements ShieldType {

	private final String configName;
	private final int enchantability;

	public GenericShieldType(String configName, int enchantability) {
		this.configName = configName;
		this.enchantability = enchantability;
	}

	@Override
	public String getConfigName() {
		return configName;
	}

	@Override
	public int getColor() {
		return 0;
	}

	@Override
	public int getEnchantability() {
		return enchantability;
	}
}
