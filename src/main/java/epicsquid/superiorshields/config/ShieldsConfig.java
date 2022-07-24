package epicsquid.superiorshields.config;

import epicsquid.superiorshields.shield.*;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.HashMap;
import java.util.Map;

public class ShieldsConfig {

	public final Map<IShieldType, IShieldConfig> SHIELDS;

	public final ForgeConfigSpec.ConfigValue<Integer> MANA_CONSUMPTION;
	public final ForgeConfigSpec.ConfigValue<Integer> ENERGY_CONSUMPTION;

	public ShieldsConfig(ForgeConfigSpec.Builder builder) {

		builder.push("consumption");
		builder.comment("The amount of a resource to use for a given shield type.");
		MANA_CONSUMPTION = builder.comment("The amount of botania mana to consume when recharging a single shield HP.").define("botania_mana", 400);
		ENERGY_CONSUMPTION = builder.comment("The amount of forge energy (FE) to consume when recharging a single shield HP.").define("energy", 400);
		builder.pop();

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

		builder.push("energy");
		builder.comment("Shields made from rechargable materials");
		for (IShieldType type : EnergyShield.values()) {
			var shieldConfig = new ShieldConfig(builder, type);
			shieldConfig.addTo(SHIELDS);
		}
		builder.pop();

		builder.push("thermal");
		builder.comment("Thermal augmentable shields stats. Each one represents a tier based on the integral components");
		for (IShieldType type : AugmentableShield.values()) {
			var shieldConfig = new ShieldConfig(builder, type);
			shieldConfig.addTo(SHIELDS);
		}
		builder.pop();

		builder.push("twilight_forest");
		builder.comment("Shields made from materials from present in the Twilight Forest");
		for (IShieldType type : TwilightForestShield.values()) {
			var shieldConfig = new ShieldConfig(builder, type);
			shieldConfig.addTo(SHIELDS);
		}
		builder.pop();


	}
}
