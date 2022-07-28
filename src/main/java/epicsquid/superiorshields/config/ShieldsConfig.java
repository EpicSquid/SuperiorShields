package epicsquid.superiorshields.config;

import epicsquid.superiorshields.shield.*;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.HashMap;
import java.util.Map;

public class ShieldsConfig {

	public final Map<IShieldType, IShieldConfig> SHIELDS;

	public final ForgeConfigSpec.ConfigValue<Integer> MANA_CONSUMPTION;
	public final ForgeConfigSpec.ConfigValue<Integer> ENERGY_CONSUMPTION;
	public final ForgeConfigSpec.ConfigValue<Float> NOVA_RANGE;
	public final ForgeConfigSpec.ConfigValue<Integer> NOVA_EFFECT_DURATION;
	public final ForgeConfigSpec.ConfigValue<Integer> SPIKE_EFFECT_DURATION;
	public final ForgeConfigSpec.ConfigValue<Float> AMPLIFY_DAMAGE_MULTIPLIER;
	public final ForgeConfigSpec.ConfigValue<Integer> AMPLIFY_SHIELD_DRAIN;
	public final ForgeConfigSpec.ConfigValue<Float> RAGING_DAMAGE_ADDED;

	public ShieldsConfig(ForgeConfigSpec.Builder builder) {

		builder.push("consumption");
		builder.comment("The amount of a resource to use for a given shield type.");
		MANA_CONSUMPTION = builder.comment("The amount of botania mana to consume when recharging a single shield HP.").define("botania_mana", 400);
		ENERGY_CONSUMPTION = builder.comment("The amount of forge energy (FE) to consume when recharging a single shield HP.").define("energy", 400);
		builder.pop();

		builder.push("enchantments");
		builder.comment("Configuration for power of various enchantments in the mod.");
		NOVA_RANGE = builder.comment("The radius of a nova enchantment's triggered effect.").define("nova_range", 1.5f);
		NOVA_EFFECT_DURATION = builder.comment("The duration of the effect applied by the triggered nova enchantment.").define("nova_effect_duration", 5);
		SPIKE_EFFECT_DURATION = builder.comment("The duration of the effect applied when a mob attacks you.").define("spike_effect_duration", 2);
		AMPLIFY_DAMAGE_MULTIPLIER = builder.comment("The damage multiplier for attacks of the amplify enchantment.").define("amplify_damage_multiplier", 1.5f);
		RAGING_DAMAGE_ADDED = builder.comment("The damage added to attacks while shields are depleted with the raging enchantment.").define("raging_damage_added", 2.0f);
		AMPLIFY_SHIELD_DRAIN = builder.comment("The amount of shield HP drained when the amplify enchantment is triggered.").define("amplify_shield_drain", 3);
		builder.pop();

		builder.push("shields");
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
		builder.comment("Shields made from materials present in the Twilight Forest");
		for (IShieldType type : TwilightForestShield.values()) {
			var shieldConfig = new ShieldConfig(builder, type);
			shieldConfig.addTo(SHIELDS);
		}
		for (IShieldType type : FieryShield.values()) {
			var shieldConfig = new ShieldConfig(builder, type);
			shieldConfig.addTo(SHIELDS);
		}
		builder.pop();

		builder.push("malum");
		builder.comment("Shields made from materials present in Malum");
		for (IShieldType type : MalumShield.values()) {
			var shieldConfig = new ShieldConfig(builder, type);
			shieldConfig.addTo(SHIELDS);
		}
		builder.pop();

		builder.pop();
	}
}
