package epicsquid.superiorshields.lang;

import com.tterrag.registrate.Registrate;
import epicsquid.superiorshields.SuperiorShields;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import top.theillusivec4.curios.api.CuriosApi;

public class ModLang {

	private static final Registrate REGISTRATE = SuperiorShields.registrate();

	public static final MutableComponent BLANK = REGISTRATE.addLang("tooltip", new ResourceLocation(SuperiorShields.MODID, "blank"), "");
	public static final MutableComponent HP = REGISTRATE.addLang("tooltip", new ResourceLocation(SuperiorShields.MODID, "hp"), " %s Hit Points");
	public static final MutableComponent RECHARGE_RATE = REGISTRATE.addLang("tooltip", new ResourceLocation(SuperiorShields.MODID, "recharge_rate"), " %s Recharge Rate");
	public static final MutableComponent RECHARGE_DELAY = REGISTRATE.addLang("tooltip", new ResourceLocation(SuperiorShields.MODID, "recharge_delay"), " %s Recharge Delay");
	public static final MutableComponent ENERGY = REGISTRATE.addLang("tooltip", new ResourceLocation(SuperiorShields.MODID, "energy"), "Energy: %s / %sK FE");
	public static final MutableComponent EQUIP = REGISTRATE.addLang("tooltip", new ResourceLocation(SuperiorShields.MODID, "equip"), "When in the Superior Shield curios slot:");

	public static final MutableComponent SHIELD_CURIOS = REGISTRATE.addRawLang(CuriosApi.MODID + ".identifier.superior_shield", "Superior Shield");
	public static final MutableComponent MODIFIERS = REGISTRATE.addRawLang(CuriosApi.MODID + ".modifiers.superior_shield", "When in the Superior Shield curios slot:");
	public static final MutableComponent CREATIVE_TAB = REGISTRATE.addRawLang("itemGroup." + SuperiorShields.MODID, "Superior Shields");

	public static final MutableComponent FIRE_NOVA = registerEnchantmentDescription("fire_nova", "On depletion, lights all mobs around you on fire.");
	public static final MutableComponent FROST_NOVA = registerEnchantmentDescription("frost_nova", "On depletion, slows and weakens all mobs around you.");
	public static final MutableComponent SHULKING_NOVA = registerEnchantmentDescription("shulking_nova", "On depletion, makes all mobs around you float.");
	public static final MutableComponent POISON_SPIKES = registerEnchantmentDescription("poison_spikes", "On damage to shield, magical spikes will poison the attacker.");
	public static final MutableComponent WITHER_SPIKES = registerEnchantmentDescription("wither_spikes", "On damage to shield, magical spikes will wither the attacker.");
	public static final MutableComponent CURING = registerEnchantmentDescription("curing", "On depletion, cures you of all potion effects, just like milk.");
	public static final MutableComponent CAPACITY = registerEnchantmentDescription("capacity", "Increases shield capacity.");
	public static final MutableComponent QUICKENED = registerEnchantmentDescription("quickened", "Increases shield recharge rate.");
	public static final MutableComponent RAGING = registerEnchantmentDescription("raging", "Increases your damage while your shield is depleted.");
	public static final MutableComponent AMPLIFY = registerEnchantmentDescription("amplify", "While your shield is full, your next attack will deal significantly greater damage, at the cost of some shield energy.");

	public static MutableComponent registerEnchantmentDescription(String ench, String desc) {
		return REGISTRATE.addRawLang("enchantment." + SuperiorShields.MODID + "." + ench + ".desc", desc);
	}

	public static void classload() {
	}

}
