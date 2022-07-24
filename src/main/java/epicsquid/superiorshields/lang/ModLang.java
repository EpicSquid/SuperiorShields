package epicsquid.superiorshields.lang;

import com.tterrag.registrate.Registrate;
import epicsquid.superiorshields.SuperiorShields;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import top.theillusivec4.curios.api.CuriosApi;

public class ModLang {

	private static final Registrate REGISTRATE = SuperiorShields.registrate();

	public static final TranslatableComponent BLANK = REGISTRATE.addLang("tooltip", new ResourceLocation(SuperiorShields.MODID, "blank"), "");
	public static final TranslatableComponent HP = REGISTRATE.addLang("tooltip", new ResourceLocation(SuperiorShields.MODID, "hp"), " %s Hit Points");
	public static final TranslatableComponent RECHARGE_RATE = REGISTRATE.addLang("tooltip", new ResourceLocation(SuperiorShields.MODID, "recharge_rate"), " %s Recharge Rate");
	public static final TranslatableComponent RECHARGE_DELAY = REGISTRATE.addLang("tooltip", new ResourceLocation(SuperiorShields.MODID, "recharge_delay"), " %s Recharge Delay");
	public static final TranslatableComponent EQUIP = REGISTRATE.addLang("tooltip", new ResourceLocation(SuperiorShields.MODID, "equip"), "When in the curios slot:");

	public static final TranslatableComponent SHIELD_CURIOS = REGISTRATE.addRawLang(CuriosApi.MODID + ".identifier.superior_shield", "Superior Shield");
	public static final TranslatableComponent CREATIVE_TAB = REGISTRATE.addRawLang("itemGroup." + SuperiorShields.MODID, "Superior Shields");

	public static final TranslatableComponent FIRE_NOVA = REGISTRATE.addRawLang(SuperiorShields.MODID + ".effect.fire_nova", "On depletion, lights all mobs around you ablaze.");
	public static final TranslatableComponent CURING = REGISTRATE.addRawLang(SuperiorShields.MODID + ".effect.curing", "On depletion, cures you of all potion effects, just like milk.");

	public static void classload() {}

}
