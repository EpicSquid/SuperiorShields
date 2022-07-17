package epicsquid.superiorshields.config;

import epicsquid.superiorshields.shield.IShieldType;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Locale;
import java.util.Map;

public class ShieldConfig implements IShieldConfig {

	private final ForgeConfigSpec.ConfigValue<Float> CAPACITY;
	private final ForgeConfigSpec.ConfigValue<Integer> RATE;
	private final ForgeConfigSpec.ConfigValue<Integer> DELAY;
	private final IShieldType TYPE;

	public ShieldConfig(ForgeConfigSpec.Builder builder, IShieldType type) {
		builder.push(type.name().toLowerCase(Locale.ROOT) + "_shield");
		TYPE = type;
		CAPACITY = builder.comment("The amount of health the shield has. One point is half a shield icon (same as hearts.)").define("capacity", type.getDefaultCapacity());
		RATE = builder.comment("The amount of ticks between each attempt to recharge the shield bar.").define("recharge_rate", type.getDefaultRate());
		DELAY = builder.comment("The amount of ticks without taking damage before the shield begins to recharge.").define("recharge_delay", type.getDefaultDelay());
		builder.pop();
	}

	public void addTo(Map<IShieldType, IShieldConfig> map) {
		map.put(TYPE, this);
	}

	@Override
	public float getCapacity() {
		return CAPACITY.get();
	}

	@Override
	public int getRate() {
		return RATE.get();
	}

	@Override
	public int getDelay() {
		return DELAY.get();
	}
}
