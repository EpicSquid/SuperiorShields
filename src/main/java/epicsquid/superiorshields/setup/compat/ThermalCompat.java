package epicsquid.superiorshields.setup.compat;

import epicsquid.superiorshields.item.EnergyShieldItem;
import epicsquid.superiorshields.item.ThermalShieldItem;
import epicsquid.superiorshields.shield.IEnergyShield;
import net.minecraft.world.item.Item;

import static epicsquid.superiorshields.setup.compat.ModCompat.THERMAL_LOADED;

public class ThermalCompat {
	public static class LoadedOnly {

		public static EnergyShieldItem makeThermalShieldOrDefault(Item.Properties props, IEnergyShield type) {
			return THERMAL_LOADED ? new ThermalShieldItem(props, type) : new EnergyShieldItem(props, type);
		}
	}
}
