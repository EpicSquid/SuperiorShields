package epicsquid.superiorshields.item;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.ItemEntry;
import epicsquid.superiorshields.SuperiorShields;
import epicsquid.superiorshields.shield.BotaniaShield;
import epicsquid.superiorshields.shield.VanillaShield;
import epicsquid.superiorshields.tags.ModTags;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import top.theillusivec4.curios.api.CuriosApi;

public class ModItems {

	private static final Registrate REGISTRATE = SuperiorShields.registrate();

	private static final TagKey<Item> CURIOS_TAG = ItemTags.create(new ResourceLocation(CuriosApi.MODID, SuperiorShields.SHIELD_CURIO));
	private static final TagKey<Item> SHIELD_TAG = ItemTags.create(new ResourceLocation(SuperiorShields.MODID, "shield"));
	private static final TagKey<Item> ENDER_PEARLS = ItemTags.create(new ResourceLocation("forge", "ender_pearls"));
	private static final TagKey<Item> MANASTEEL_INGOT = ItemTags.create(new ResourceLocation("forge", "ingots/manasteel"));
	private static final TagKey<Item> TERRASTEEL_INGOT = ItemTags.create(new ResourceLocation("forge", "ingots/terrasteel"));
	private static final TagKey<Item> ELEMENTIUM_INGOT = ItemTags.create(new ResourceLocation("forge", "ingots/elementium"));

	public static final ItemEntry<VanillaShieldItem> IRON_SHIELD = REGISTRATE.item("iron_shield", props -> new VanillaShieldItem(props, VanillaShield.IRON)).tab(() -> SuperiorShields.ITEM_GROUP)
					.tag(CURIOS_TAG)
					.tag(SHIELD_TAG)
					.recipe((ctx, p) -> {
						ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
										.pattern(" X ")
										.pattern("XEX")
										.pattern(" X ")
										.define('X', ItemTags.create(new ResourceLocation("forge", "ingots/iron")))
										.define('E', ENDER_PEARLS)
										.unlockedBy("has_enderpearl", DataIngredient.items(Items.ENDER_PEARL).getCritereon(p))
										.save(p, p.safeId(ctx.getEntry()));
					})
					.properties(props -> props.durability(Tiers.IRON.getUses()))
					.register();
	public static final ItemEntry<VanillaShieldItem> COPPER_SHIELD = REGISTRATE.item("copper_shield", props -> new VanillaShieldItem(props, VanillaShield.COPPER)).tab(() -> SuperiorShields.ITEM_GROUP)
					.tag(CURIOS_TAG)
					.tag(SHIELD_TAG)
					.recipe((ctx, p) -> {
						ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
										.pattern(" X ")
										.pattern("XEX")
										.pattern(" X ")
										.define('X', ItemTags.create(new ResourceLocation("forge", "ingots/copper")))
										.define('E', ENDER_PEARLS)
										.unlockedBy("has_enderpearl", DataIngredient.items(Items.ENDER_PEARL).getCritereon(p))
										.save(p, p.safeId(ctx.getEntry()));
					})
					.properties(props -> props.durability(160))
					.register();
	public static final ItemEntry<VanillaShieldItem> GOLDEN_SHIELD = REGISTRATE.item("golden_shield",
									props -> new VanillaShieldItem(props, VanillaShield.GOLD)).tab(() -> SuperiorShields.ITEM_GROUP)
					.tag(CURIOS_TAG)
					.tag(SHIELD_TAG)
					.recipe((ctx, p) -> {
						ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
										.pattern(" X ")
										.pattern("XEX")
										.pattern(" X ")
										.define('X', ItemTags.create(new ResourceLocation("forge", "ingots/gold")))
										.define('E', ENDER_PEARLS)
										.unlockedBy("has_enderpearl", DataIngredient.items(Items.ENDER_PEARL).getCritereon(p))
										.save(p, p.safeId(ctx.getEntry()));
					})
					.properties(props -> props.durability(Tiers.GOLD.getUses()))
					.register();
	public static final ItemEntry<VanillaShieldItem> DIAMOND_SHIELD = REGISTRATE.item("diamond_shield",
									props -> new VanillaShieldItem(props, VanillaShield.DIAMOND)).tab(() -> SuperiorShields.ITEM_GROUP)
					.tag(CURIOS_TAG)
					.tag(SHIELD_TAG)
					.recipe((ctx, p) -> {
						ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
										.pattern(" X ")
										.pattern("XEX")
										.pattern(" X ")
										.define('X', ItemTags.create(new ResourceLocation("forge", "gems/diamond")))
										.define('E', ENDER_PEARLS)
										.unlockedBy("has_enderpearl", DataIngredient.items(Items.ENDER_PEARL).getCritereon(p))
										.save(p, p.safeId(ctx.getEntry()));
					})
					.properties(props -> props.durability(Tiers.DIAMOND.getUses()))
					.register();

	public static final ItemEntry<VanillaShieldItem> NETHERITE_SHIELD = REGISTRATE.item("netherite_shield",
									props -> new VanillaShieldItem(props, VanillaShield.NETHERITE)).tab(() -> SuperiorShields.ITEM_GROUP)
					.tag(CURIOS_TAG)
					.tag(SHIELD_TAG)
					.recipe((ctx, p) -> {
						UpgradeRecipeBuilder.smithing(Ingredient.of(DIAMOND_SHIELD.get()), Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ingots/netherite"))), ctx.get())
										.unlocks("has_netherite", DataIngredient.tag(ItemTags.create(new ResourceLocation("forge", "ingots/netherite"))).getCritereon(p))
										.save(p, p.safeId(ctx.getEntry()));
					})
					.properties(props -> props.durability(Tiers.NETHERITE.getUses()))
					.register();

	public static final ItemEntry<BotaniaShieldItem> MANASTEEL_SHIELD = REGISTRATE.item("manasteel_shield",
									props -> new BotaniaShieldItem(props, BotaniaShield.MANASTEEL)).tab(() -> SuperiorShields.ITEM_GROUP)
					.tag(CURIOS_TAG)
					.tag(SHIELD_TAG)
					.recipe((ctx, p) -> {
						ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
										.pattern(" X ")
										.pattern("XEX")
										.pattern(" X ")
										.define('X', MANASTEEL_INGOT)
										.define('E', ModTags.MANAPEARL)
										.unlockedBy("has_manasteel", DataIngredient.tag(MANASTEEL_INGOT).getCritereon(p))
										.save(p, p.safeId(ctx.getEntry()));
					})
					.register();

	public static final ItemEntry<BotaniaShieldItem> TERRASTEEL_SHIELD = REGISTRATE.item("terrasteel_shield",
									props -> new BotaniaShieldItem(props, BotaniaShield.TERRASTEEL)).tab(() -> SuperiorShields.ITEM_GROUP)
					.tag(CURIOS_TAG)
					.tag(SHIELD_TAG)
					.recipe((ctx, p) -> {
						ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
										.pattern(" X ")
										.pattern("XEX")
										.pattern(" X ")
										.define('X', TERRASTEEL_INGOT)
										.define('E', MANASTEEL_SHIELD.get())
										.unlockedBy("has_terrasteel", DataIngredient.tag(TERRASTEEL_INGOT).getCritereon(p))
										.save(p, p.safeId(ctx.getEntry()));
					})
					.register();

	public static final ItemEntry<BotaniaShieldItem> ELEMENTIUM_SHIELD = REGISTRATE.item("elementium_shield",
									props -> new BotaniaShieldItem(props, BotaniaShield.ELEMENTIUM)).tab(() -> SuperiorShields.ITEM_GROUP)
					.tag(CURIOS_TAG)
					.tag(SHIELD_TAG)
					.recipe((ctx, p) -> {
						ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
										.pattern(" X ")
										.pattern("XEX")
										.pattern(" X ")
										.define('X', ELEMENTIUM_INGOT)
										.define('E', ModTags.PIXIE_DUST)
										.unlockedBy("has_elementium", DataIngredient.tag(ELEMENTIUM_INGOT).getCritereon(p))
										.save(p, p.safeId(ctx.getEntry()));
					})
					.register();

	public static void classload() {
	}
}
