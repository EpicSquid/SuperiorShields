package epicsquid.superiorshields.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {

	public static final ShieldsConfig SHIELD;
	public static final ForgeConfigSpec SHIELD_SPEC;

	static {
		Pair<ShieldsConfig, ForgeConfigSpec> shieldSpecPair = new ForgeConfigSpec.Builder().configure(ShieldsConfig::new);
		SHIELD = shieldSpecPair.getLeft();
		SHIELD_SPEC = shieldSpecPair.getRight();
	}
}
