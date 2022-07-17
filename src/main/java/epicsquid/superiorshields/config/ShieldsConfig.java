package epicsquid.superiorshields.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.HashMap;
import java.util.Map;

public class ShieldsConfig {

	public final Map<String, IShieldConfig> SHIELDS;

	public ShieldsConfig(ForgeConfigSpec.Builder builder) {
		SHIELDS = new HashMap<>();

		builder.push("vanilla");
		builder.comment("Shields made from materials present in Vanilla");
		SHIELDS.put("iron", new ShieldConfig(builder, "iron_shield", 5f, 80, 40));
		SHIELDS.put("gold", new ShieldConfig(builder, "gold_shield", 3f, 20, 40));
		SHIELDS.put("diamond", new ShieldConfig(builder, "diamond_shield", 7f, 60, 40));
		SHIELDS.put("netherite", new ShieldConfig(builder, "netherite_shield", 8f, 40, 40));
		builder.pop();

		builder.push("botania");
		builder.comment("Shields made from materials available in Botania");
		SHIELDS.put("manasteel", new ShieldConfig(builder, "manasteel_shield", 5f, 80, 40));
		SHIELDS.put("terrasteel", new ShieldConfig(builder, "terrasteel_shield", 9f, 60, 40));
		SHIELDS.put("elementium", new ShieldConfig(builder, "elementium_shield", 6f, 60, 20));
		builder.pop();
	}
}
