package epicsquid.superiorshields.setup.compat;

import com.tterrag.registrate.util.nullness.NonNullFunction;
import epicsquid.superiorshields.item.BotaniaShieldItem;
import epicsquid.superiorshields.item.SuperiorShieldItem;
import epicsquid.superiorshields.item.VanillaShieldItem;
import epicsquid.superiorshields.shield.IShieldType;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;

public class BotaniaCompat {

	private static boolean LOADED;

	public static void init() {
		LOADED = ModList.get().isLoaded("botania");
	}

	public static NonNullFunction<Item.Properties, SuperiorShieldItem<IShieldType>> makeBotaniaShieldOrDefault(IShieldType type) {
		return LOADED ? LoadedOnly.makeBotaniaShield(type) : props -> new VanillaShieldItem(props.durability(512), type);
	}

	public static class LoadedOnly {

		public static NonNullFunction<Item.Properties, SuperiorShieldItem<IShieldType>> makeBotaniaShield(IShieldType type) {
			return props -> new BotaniaShieldItem(props, type);
		}
	}
}
