package epicsquid.superiorshields.setup.compat;

import com.tterrag.registrate.util.nullness.NonNullFunction;
import epicsquid.superiorshields.item.SoulStainedSteelShield;
import epicsquid.superiorshields.item.SpiritHunterShield;
import epicsquid.superiorshields.item.SuperiorShieldItem;
import epicsquid.superiorshields.item.VanillaShieldItem;
import epicsquid.superiorshields.shield.IShieldType;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;

public class MalumCompat {

	private static boolean LOADED;

	public static void init() {
		LOADED = ModList.get().isLoaded("malum");
	}

	public static NonNullFunction<Item.Properties, SuperiorShieldItem<IShieldType>> makeSoulStainedShieldOrDefault(IShieldType type) {
		return LOADED ? LoadedOnly.makeSoulStainedShield(type) : props -> new VanillaShieldItem(props, type);
	}

	public static NonNullFunction<Item.Properties, SuperiorShieldItem<IShieldType>> makeSpiritHunterShieldOrDefault(IShieldType type) {
		return LOADED ? LoadedOnly.makeSpiritHunterShield(type) : props -> new VanillaShieldItem(props, type);
	}


	public static class LoadedOnly {

		public static NonNullFunction<Item.Properties, SuperiorShieldItem<IShieldType>> makeSoulStainedShield(IShieldType type) {
			return props -> new SoulStainedSteelShield(props, type);
		}

		public static NonNullFunction<Item.Properties, SuperiorShieldItem<IShieldType>> makeSpiritHunterShield(IShieldType type) {
			return props -> new SpiritHunterShield(props, type);
		}
	}
}
