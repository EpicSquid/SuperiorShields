package epicsquid.superiorshields.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ShieldConfig implements IShieldConfig {

	private final ForgeConfigSpec.ConfigValue<Float> CAPACITY;
	private final ForgeConfigSpec.ConfigValue<Integer> RATE;
	private final ForgeConfigSpec.ConfigValue<Integer> DELAY;

	public ShieldConfig(ForgeConfigSpec.Builder builder, String name, float capacity, int rate, int delay) {
		builder.push(name);
		CAPACITY = builder.comment("The amount of health the shield has. One point is half a shield icon (same as hearts.)").define("capacity", capacity);
		RATE = builder.comment("The amount of ticks between each attempt to recharge the shield bar.").define("recharge_rate", rate);
		DELAY = builder.comment("The amount of ticks without taking damage before the shield begins to recharge.").define("recharge_delay", delay);
		builder.pop();
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
