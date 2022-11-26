package epicsquid.superiorshields.setup.compat;

import com.tterrag.registrate.util.nullness.NonNullFunction;
import epicsquid.superiorshields.item.ArsShieldItem;
import epicsquid.superiorshields.item.SuperiorShieldItem;
import epicsquid.superiorshields.item.VanillaShieldItem;
import epicsquid.superiorshields.shield.IShieldType;
import epicsquid.superiorshields.shield.effect.EffectTrigger;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;

public class ArsCompat {

	private static boolean LOADED;

	public static void init() {
		LOADED = ModList.get().isLoaded("ars_nouveau");
	}

	public static NonNullFunction<Item.Properties, SuperiorShieldItem<IShieldType>> makeArsShieldOrDefault(IShieldType type) {
		return LOADED ? LoadedOnly.makeArsShield(type) : props -> new VanillaShieldItem(props.durability(512), type);
	}

	public static void reactiveTrigger(ItemStack shield, Player player, EffectTrigger trigger) {
		if (LOADED) {
			LoadedOnly.triggerReactive(shield, player, trigger);
		}
	}

	public static class LoadedOnly {

		public static NonNullFunction<Item.Properties, SuperiorShieldItem<IShieldType>> makeArsShield(IShieldType type) {
			return props -> new ArsShieldItem(props, type);
		}

		public static void triggerReactive(ItemStack shield, Player player, EffectTrigger trigger) {
			ArsShieldItem.triggerReactive(shield, player, trigger);
		}
	}
}
