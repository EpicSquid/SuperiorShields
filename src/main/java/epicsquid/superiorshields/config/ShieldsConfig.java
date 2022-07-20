package epicsquid.superiorshields.config;

import epicsquid.superiorshields.shield.*;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.HashMap;
import java.util.Map;

public class ShieldsConfig {

	public final Map<IShieldType, IShieldConfig> SHIELDS;

	public ShieldsConfig(ForgeConfigSpec.Builder builder) {
		SHIELDS = new HashMap<>();

		builder.push("vanilla");
		builder.comment("Shields made from materials present in Vanilla");
		for (IShieldType type : VanillaShield.values()) {
			var shieldConfig = new ShieldConfig(builder, type);
			shieldConfig.addTo(SHIELDS);
		}
		builder.pop();

		builder.push("metal");
		builder.comment("Shields made from materials often present in a variety of mods");
		for (IShieldType type : MetalShield.values()) {
			var shieldConfig = new ShieldConfig(builder, type);
			shieldConfig.addTo(SHIELDS);
		}
		builder.pop();

		builder.push("mekanism_metals");
		builder.comment("Shields made from materials present in Mekanism");
		for (IShieldType type : MekanismMetalShield.values()) {
			var shieldConfig = new ShieldConfig(builder, type);
			shieldConfig.addTo(SHIELDS);
		}
		builder.pop();

		builder.push("botania");
		builder.comment("Shields made from materials available in Botania");
		for (IShieldType type : BotaniaShield.values()) {
			var shieldConfig = new ShieldConfig(builder, type);
			shieldConfig.addTo(SHIELDS);
		}
		builder.pop();
	}
}
