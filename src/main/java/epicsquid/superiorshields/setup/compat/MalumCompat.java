package epicsquid.superiorshields.setup.compat;

import epicsquid.superiorshields.item.*;
import epicsquid.superiorshields.shield.IShieldType;
import net.minecraft.world.item.Item;

import static epicsquid.superiorshields.setup.compat.ModCompat.BOTANIA_LOADED;
import static epicsquid.superiorshields.setup.compat.ModCompat.MALUM_LOADED;

public class MalumCompat {

	public static class LoadedOnly {

		public static SuperiorShieldItem<IShieldType> makeSoulStainedShieldOrDefault(Item.Properties props, IShieldType type) {
			return MALUM_LOADED ? new SoulStainedSteelShield(props, type) : new VanillaShieldItem(props, type);
		}

		public static SuperiorShieldItem<IShieldType> makeSpiritHunterOrDefault(Item.Properties props, IShieldType type) {
			return MALUM_LOADED ? new SpiritHunterShield(props, type) : new VanillaShieldItem(props, type);
		}
	}
}
