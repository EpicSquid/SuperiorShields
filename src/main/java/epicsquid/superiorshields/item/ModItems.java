package epicsquid.superiorshields.item;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.ItemEntry;
import epicsquid.superiorshields.SuperiorShields;
import epicsquid.superiorshields.shield.*;
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
import net.minecraftforge.common.Tags;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Locale;

public class ModItems {

	private static final Registrate REGISTRATE = SuperiorShields.registrate();

	private static final TagKey<Item> CURIOS_TAG = ItemTags.create(new ResourceLocation(CuriosApi.MODID, SuperiorShields.SHIELD_CURIO));
	private static final TagKey<Item> SHIELD_TAG = ItemTags.create(new ResourceLocation(SuperiorShields.MODID, "shield"));
	private static final TagKey<Item> MANASTEEL_INGOT = ItemTags.create(new ResourceLocation("forge", "ingots/manasteel"));
	private static final TagKey<Item> TERRASTEEL_INGOT = ItemTags.create(new ResourceLocation("forge", "ingots/terrasteel"));
	private static final TagKey<Item> ELEMENTIUM_INGOT = ItemTags.create(new ResourceLocation("forge", "ingots/elementium"));
	private static final TagKey<Item> STEEL_INGOT = ItemTags.create(new ResourceLocation("forge", "ingots/steel"));

	private static final TagKey<Item> ADVANCED_ALLOY = ItemTags.create(new ResourceLocation("forge", "alloys/advanced"));
	private static final TagKey<Item> TIN_GEAR = ItemTags.create(new ResourceLocation("forge", "gears/tin"));
	public static final ItemEntry<VanillaShieldItem> IRON_SHIELD = vanillaShieldItem(VanillaShield.IRON, Tiers.IRON.getUses()).register();
	public static final ItemEntry<VanillaShieldItem> COPPER_SHIELD = vanillaShieldItem(VanillaShield.COPPER, 160).register();
	public static final ItemEntry<VanillaShieldItem> GOLDEN_SHIELD = REGISTRATE.item("golden_shield", props -> new VanillaShieldItem(props, VanillaShield.GOLD)).tab(() -> SuperiorShields.ITEM_GROUP)
					.tag(CURIOS_TAG)
					.tag(SHIELD_TAG)
					.recipe((ctx, p) -> {
						ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
										.pattern(" X ")
										.pattern("XEX")
										.pattern(" X ")
										.define('X', Tags.Items.INGOTS_GOLD)
										.define('E', Tags.Items.ENDER_PEARLS)
										.unlockedBy("has_enderpearl", DataIngredient.items(Items.ENDER_PEARL).getCritereon(p))
										.save(p, p.safeId(ctx.getEntry()));
					})
					.properties(props -> props.durability(Tiers.GOLD.getUses())).register();
	public static final ItemEntry<VanillaShieldItem> TIN_SHIELD = vanillaShieldItem(MetalShield.TIN, 32).register();
	public static final ItemEntry<VanillaShieldItem> LEAD_SHIELD = vanillaShieldItem(MetalShield.LEAD, 64).register();
	public static final ItemEntry<VanillaShieldItem> SILVER_SHIELD = vanillaShieldItem(MetalShield.SILVER, 48).register();
	public static final ItemEntry<VanillaShieldItem> NICKEL_SHIELD = vanillaShieldItem(MetalShield.NICKEL, 240).register();
	public static final ItemEntry<VanillaShieldItem> BRONZE_SHIELD = vanillaShieldItem(MetalShield.BRONZE, 325).register();
	public static final ItemEntry<VanillaShieldItem> ELECTRUM_SHIELD = vanillaShieldItem(MetalShield.ELECTRUM, 192).register();
	public static final ItemEntry<VanillaShieldItem> INVAR_SHIELD = vanillaShieldItem(MetalShield.INVAR, 300).register();
	public static final ItemEntry<VanillaShieldItem> CONSTANTAN_SHIELD = vanillaShieldItem(MetalShield.CONSTANTAN, 250).register();
	public static final ItemEntry<VanillaShieldItem> OSMIUM_SHIELD = vanillaShieldItem(MekanismMetalShield.OSMIUM, 250).register();
	public static final ItemEntry<VanillaShieldItem> STEEL_SHIELD = vanillaShieldItem(MekanismMetalShield.STEEL, 500).register();
	public static final ItemEntry<VanillaShieldItem> REFINED_OBSIDIAN_SHIELD = vanillaShieldItem(MekanismMetalShield.REFINED_OBSIDIAN, 1680).register();
	public static final ItemEntry<VanillaShieldItem> REFINED_GLOWSTONE_SHIELD = vanillaShieldItem(MekanismMetalShield.REFINED_GLOWSTONE, 384).register();
	public static final ItemEntry<VanillaShieldItem> IRONWOOD = vanillaShieldItem(TwilightForestShield.IRONWOOD, 512).register();
	public static final ItemEntry<VanillaShieldItem> STEELLEAF = vanillaShieldItem(TwilightForestShield.STEELLEAF, 512).register();
	public static final ItemEntry<VanillaShieldItem> LAPIS_SHIELD = REGISTRATE.item("lapis_shield", props -> new VanillaShieldItem(props, MekanismMetalShield.LAPIS)).tab(() -> SuperiorShields.ITEM_GROUP)
					.tag(CURIOS_TAG)
					.tag(SHIELD_TAG)
					.recipe((ctx, p) -> {
						ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
										.pattern(" X ")
										.pattern("XEX")
										.pattern(" X ")
										.define('X', Tags.Items.GEMS_LAPIS)
										.define('E', Tags.Items.ENDER_PEARLS)
										.unlockedBy("has_enderpearl", DataIngredient.tag(Tags.Items.ENDER_PEARLS).getCritereon(p))
										.save(p, p.safeId(ctx.getEntry()));
					})
					.properties(props -> props.durability(128)).register();

	public static final ItemEntry<VanillaShieldItem> DIAMOND_SHIELD = REGISTRATE.item("diamond_shield",
									props -> new VanillaShieldItem(props, VanillaShield.DIAMOND)).tab(() -> SuperiorShields.ITEM_GROUP)
					.tag(CURIOS_TAG)
					.tag(SHIELD_TAG)
					.recipe((ctx, p) -> {
						ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
										.pattern(" X ")
										.pattern("XEX")
										.pattern(" X ")
										.define('X', Tags.Items.GEMS_DIAMOND)
										.define('E', Items.ENDER_EYE)
										.unlockedBy("has_eye_of_ender", DataIngredient.items(Items.ENDER_EYE).getCritereon(p))
										.save(p, p.safeId(ctx.getEntry()));
					})
					.properties(props -> props.durability(Tiers.DIAMOND.getUses()))
					.register();

	public static final ItemEntry<VanillaShieldItem> NETHERITE_SHIELD = REGISTRATE.item("netherite_shield",
									props -> new VanillaShieldItem(props, VanillaShield.NETHERITE)).tab(() -> SuperiorShields.ITEM_GROUP)
					.tag(CURIOS_TAG)
					.tag(SHIELD_TAG)
					.recipe((ctx, p) -> {
						UpgradeRecipeBuilder.smithing(Ingredient.of(DIAMOND_SHIELD.get()), Ingredient.of(Tags.Items.INGOTS_NETHERITE), ctx.get())
										.unlocks("has_netherite", DataIngredient.tag(Tags.Items.INGOTS_NETHERITE).getCritereon(p))
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

	// Mekanism Electric Shield
	public static final ItemEntry<EnergyShieldItem> ELECTRIC_SHIELD = REGISTRATE.item("electric_shield", props -> new EnergyShieldItem(props, EnergyShield.ELECTRIC_SHIELD)).tab(() -> SuperiorShields.ITEM_GROUP)
					.tag(CURIOS_TAG)
					.tag(SHIELD_TAG)
					.recipe((ctx, p) -> {
						ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
										.pattern(" X ")
										.pattern("XEX")
										.pattern(" X ")
										.define('X', ADVANCED_ALLOY)
										.define('E', ItemTags.create(new ResourceLocation("forge", "batteries")))
										.unlockedBy("has_advanced_alloy", DataIngredient.tag(ADVANCED_ALLOY).getCritereon(p))
										.save(p, p.safeId(ctx.getEntry()));
					})
					.register();

	// Thermal Augmentable Shield
	public static final ItemEntry<ThermalShieldItem> FLUX_SHIELD = REGISTRATE.item("flux_shield", props -> new ThermalShieldItem(props, AugmentableShield.BASE)).tab(() -> SuperiorShields.ITEM_GROUP)
					.tag(CURIOS_TAG)
					.tag(SHIELD_TAG)
					.recipe((ctx, p) -> {
						ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
										.pattern(" G ")
										.pattern("XEX")
										.pattern(" G ")
										.define('G', TIN_GEAR)
										.define('X', Tags.Items.INGOTS_IRON)
										.define('E', ModTags.FLUX_COIL)
										.unlockedBy("has_redstone", DataIngredient.tag(Tags.Items.DUSTS_REDSTONE).getCritereon(p))
										.save(p, p.safeId(ctx.getEntry()));
					})
					.register();

	// Immersive Engineering Shield
	public static final ItemEntry<EnergyShieldItem> ENGINEERS_SHIELD = REGISTRATE.item("engineers_shield", props -> new EnergyShieldItem(props, EnergyShield.ENGINEERS_SHIELD)).tab(() -> SuperiorShields.ITEM_GROUP)
					.tag(CURIOS_TAG)
					.tag(SHIELD_TAG)
					.recipe((ctx, p) -> {
						ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
										.pattern(" G ")
										.pattern("XEX")
										.pattern(" G ")
										.define('G', STEEL_INGOT)
										.define('X', ModTags.WOODEN_GRIP)
										.define('E', ModTags.COMPONENT_STEEL)
										.unlockedBy("has_steel", DataIngredient.tag(STEEL_INGOT).getCritereon(p))
										.save(p, p.safeId(ctx.getEntry()));
					})
					.lang("Engineer's Shield")
					.register();

	public static final ItemEntry<VanillaShieldItem> KNIGHTMETAL_SHIELD = REGISTRATE.item("knightmetal_shield",
									props -> new VanillaShieldItem(props, TwilightForestShield.KNIGHTMETAL)).tab(() -> SuperiorShields.ITEM_GROUP)
					.tag(CURIOS_TAG)
					.tag(SHIELD_TAG)
					.recipe((ctx, p) -> {
						ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
										.pattern(" X ")
										.pattern("XEX")
										.pattern(" X ")
										.define('X', ItemTags.create(new ResourceLocation("forge", "ingots/knightmetal")))
										.define('E', Items.ENDER_EYE)
										.unlockedBy("has_eye_of_ender", DataIngredient.items(Items.ENDER_EYE).getCritereon(p))
										.save(p, p.safeId(ctx.getEntry()));
					})
					.properties(props -> props.durability(Tiers.DIAMOND.getUses()))
					.register();

	public static final ItemEntry<FieryShieldItem> FIERY_SHIELD = REGISTRATE.item("fiery_shield",
									props -> new FieryShieldItem(props, FieryShield.FIREY)).tab(() -> SuperiorShields.ITEM_GROUP)
					.tag(CURIOS_TAG)
					.tag(SHIELD_TAG)
					.recipe((ctx, p) -> {
						ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
										.pattern(" X ")
										.pattern("XEX")
										.pattern(" X ")
										.define('X', ItemTags.create(new ResourceLocation("forge", "ingots/fiery")))
										.define('E', Items.ENDER_EYE)
										.unlockedBy("has_eye_of_ender", DataIngredient.items(Items.ENDER_EYE).getCritereon(p))
										.save(p, p.safeId(ctx.getEntry()));
					})
					.properties(props -> props.durability(Tiers.DIAMOND.getUses()))
					.register();

	private static ItemBuilder<VanillaShieldItem, Registrate> vanillaShieldItem(IShieldType type, int durability) {
		return REGISTRATE.item(type.name().toLowerCase(Locale.ROOT) + "_shield", props -> new VanillaShieldItem(props, type)).tab(() -> SuperiorShields.ITEM_GROUP)
						.tag(CURIOS_TAG)
						.tag(SHIELD_TAG)
						.recipe((ctx, p) -> {
							ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
											.pattern(" X ")
											.pattern("XEX")
											.pattern(" X ")
											.define('X', ItemTags.create(new ResourceLocation("forge", "ingots/" + type.name().toLowerCase(Locale.ROOT))))
											.define('E', Tags.Items.ENDER_PEARLS)
											.unlockedBy("has_enderpearl", DataIngredient.tag(Tags.Items.ENDER_PEARLS).getCritereon(p))
											.save(p, p.safeId(ctx.getEntry()));
						})
						.properties(props -> props.durability(durability));
	}

	public static void classload() {
	}
}
