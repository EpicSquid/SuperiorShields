package epicsquid.superiorshields.item;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import epicsquid.superiorshields.SuperiorShields;
import epicsquid.superiorshields.shield.GenericShieldType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import top.theillusivec4.curios.api.CuriosApi;

public class ModItems {

	private static final Registrate REGISTRATE = SuperiorShields.registrate();

	private static final TagKey<Item> CURIOS_TAG = ItemTags.create(new ResourceLocation(CuriosApi.MODID, SuperiorShields.SHIELD_CURIO));

	private static final GenericShieldType IRON = new GenericShieldType(5f, 80, 40, Tiers.IRON.getUses(), Tiers.IRON.getEnchantmentValue());
	private static final GenericShieldType GOLD = new GenericShieldType(3f, 20, 40, Tiers.GOLD.getUses(), Tiers.GOLD.getEnchantmentValue());
	private static final GenericShieldType DIAMOND = new GenericShieldType(7f, 60, 40, Tiers.DIAMOND.getUses(), Tiers.DIAMOND.getEnchantmentValue());

	public static final ItemEntry<VanillaShieldItem> IRON_SHIELD = REGISTRATE.item("iron_shield",
					props -> new VanillaShieldItem(props, IRON)).tab(() -> SuperiorShields.ITEM_GROUP).tag(CURIOS_TAG).register();
	public static final ItemEntry<VanillaShieldItem> GOLDEN_SHIELD = REGISTRATE.item("golden_shield",
					props -> new VanillaShieldItem(props, GOLD)).tab(() -> SuperiorShields.ITEM_GROUP).tag(CURIOS_TAG).register();
	public static final ItemEntry<VanillaShieldItem> DIAMOND_SHIELD = REGISTRATE.item("diamond_shield",
					props -> new VanillaShieldItem(props, DIAMOND)).tab(() -> SuperiorShields.ITEM_GROUP).tag(CURIOS_TAG).register();

	public static void classload() {}
}
