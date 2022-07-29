package epicsquid.superiorshields.setup.compat;

import com.tterrag.registrate.util.nullness.NonNullFunction;
import epicsquid.superiorshields.item.EnergyShieldItem;
import epicsquid.superiorshields.shield.IEnergyShield;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;

public class ThermalCompat {

	private static boolean LOADED;

	public static void init() {
		LOADED = ModList.get().isLoaded("thermal");
	}

	public static NonNullFunction<Item.Properties, EnergyShieldItem> makeThermalShieldOrDefault(IEnergyShield type) {
//		return LOADED ? LoadedOnly.makeThermalShield(type) : props -> new EnergyShieldItem(props, type);
		return props -> new EnergyShieldItem(props, type);
	}

	public static class LoadedOnly {

//		public static NonNullFunction<Item.Properties, EnergyShieldItem> makeThermalShield(IEnergyShield type) {
//			return props -> new ThermalShieldItem(props, type);
//		}
	}
}
