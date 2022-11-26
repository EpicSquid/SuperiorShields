package epicsquid.superiorshields.setup.compat;

import com.tterrag.registrate.util.nullness.NonNullFunction;
import epicsquid.superiorshields.item.ArsShieldItem;
import epicsquid.superiorshields.item.SuperiorShieldItem;
import epicsquid.superiorshields.item.VanillaShieldItem;
import epicsquid.superiorshields.shield.IShieldType;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;

public class ArsCompat {

	private static boolean LOADED;

	public static void init() {
		LOADED = ModList.get().isLoaded("ars_nouveau");
	}

	public static NonNullFunction<Item.Properties, SuperiorShieldItem<IShieldType>> makeArsShieldOrDefault(IShieldType type) {
		return LOADED ? LoadedOnly.makeArsShield(type) : props -> new VanillaShieldItem(props.durability(512), type);
	}

	public static class LoadedOnly {

		public static NonNullFunction<Item.Properties, SuperiorShieldItem<IShieldType>> makeArsShield(IShieldType type) {
			return props -> new ArsShieldItem(props, type);
		}
	}
}
