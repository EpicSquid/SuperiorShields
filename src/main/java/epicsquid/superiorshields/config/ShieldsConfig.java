package epicsquid.superiorshields.config;

import epicsquid.superiorshields.shield.BotaniaShield;
import epicsquid.superiorshields.shield.IShieldType;
import epicsquid.superiorshields.shield.MetalShield;
import epicsquid.superiorshields.shield.VanillaShield;
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
		builder.comment("Shields made from materials present often present in a variety of mods");
		for (IShieldType type : MetalShield.values()) {
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
