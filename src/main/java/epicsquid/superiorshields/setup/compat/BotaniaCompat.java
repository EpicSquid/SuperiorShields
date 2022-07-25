package epicsquid.superiorshields.setup.compat;

import epicsquid.superiorshields.item.BotaniaShieldItem;
import epicsquid.superiorshields.item.SuperiorShieldItem;
import epicsquid.superiorshields.item.VanillaShieldItem;
import epicsquid.superiorshields.shield.IShieldType;
import net.minecraft.world.item.Item;

import static epicsquid.superiorshields.setup.compat.ModCompat.BOTANIA_LOADED;

public class BotaniaCompat {

	public static class LoadedOnly {

		public static SuperiorShieldItem<IShieldType> makeBotaniaShieldOrDefault(Item.Properties props, IShieldType type) {
			return BOTANIA_LOADED ? new BotaniaShieldItem(props, type) : new VanillaShieldItem(props.durability(512), type);
		}
	}
}
