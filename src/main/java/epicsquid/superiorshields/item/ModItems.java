package epicsquid.superiorshields.item;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.ItemEntry;
import epicsquid.superiorshields.SuperiorShields;
import epicsquid.superiorshields.setup.compat.ArsCompat;
import epicsquid.superiorshields.setup.compat.BotaniaCompat;
import epicsquid.superiorshields.setup.compat.MalumCompat;
import epicsquid.superiorshields.setup.compat.ThermalCompat;
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
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
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
	public static final ItemEntry<VanillaShieldItem> STEELEAF = vanillaShieldItem(TwilightForestShield.STEELEAF, 131).register();
	public static final ItemEntry<VanillaShieldItem> LAPIS_SHIELD = REGISTRATE.item("lapis_shield", props -> new VanillaShieldItem(props, MekanismMetalShield.LAPIS))
			.tab(() -> SuperiorShields.ITEM_GROUP)
			.tag(CURIOS_TAG)
			.tag(SHIELD_TAG)
			.recipe((ctx, p) -> {
				ConditionalRecipe.builder()
						.addCondition(new ModLoadedCondition("mekanism"))
						.addRecipe((writer) -> ShapedRecipeBuilder.shaped(ctx.getEntry())
								.pattern(" X ")
								.pattern("XEX")
								.pattern(" X ")
								.define('X', Tags.Items.GEMS_LAPIS)
								.define('E', Tags.Items.ENDER_PEARLS)
								.unlockedBy("has_enderpearl", DataIngredient.tag(Tags.Items.ENDER_PEARLS).getCritereon(p))
								.save(writer)
						)
						.generateAdvancement()
						.build(p, p.safeId(ctx.getEntry()));
			})
			.properties(props -> props.durability(128)).register();

	public static final ItemEntry<VanillaShieldItem> DIAMOND_SHIELD = REGISTRATE.item("diamond_shield", props -> new VanillaShieldItem(props, VanillaShield.DIAMOND))
			.tab(() -> SuperiorShields.ITEM_GROUP)
			.tag(CURIOS_TAG)
			.tag(SHIELD_TAG)
			.recipe((ctx, p) -> {
				ShapedRecipeBuilder.shaped(ctx.getEntry())
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

	public static final ItemEntry<VanillaShieldItem> NETHERITE_SHIELD = REGISTRATE.item("netherite_shield", props -> new VanillaShieldItem(props, VanillaShield.NETHERITE))
			.tab(() -> SuperiorShields.ITEM_GROUP)
			.tag(CURIOS_TAG)
			.tag(SHIELD_TAG)
			.recipe((ctx, p) -> {
				UpgradeRecipeBuilder.smithing(Ingredient.of(DIAMOND_SHIELD.get()), Ingredient.of(Tags.Items.INGOTS_NETHERITE), ctx.get())
						.unlocks("has_netherite", DataIngredient.tag(Tags.Items.INGOTS_NETHERITE).getCritereon(p))
						.save(p, p.safeId(ctx.getEntry()));
			})
			.properties(props -> props.durability(Tiers.NETHERITE.getUses()))
			.register();

	public static final ItemEntry<SuperiorShieldItem<IShieldType>> ENCHANTER_SHIELD = REGISTRATE.item("enchanter_shield", ArsCompat.makeArsShieldOrDefault(ArsShield.ENCHANTER_SHIELD))
			.tab(() -> SuperiorShields.ITEM_GROUP)
			.tag(CURIOS_TAG)
			.tag(SHIELD_TAG)
			.recipe((ctx, p) -> {
				ConditionalRecipe.builder()
						.addCondition(new ModLoadedCondition("ars_nouveau"))
						.addRecipe((writer) -> ShapedRecipeBuilder.shaped(ctx.getEntry())
								.pattern(" X ")
								.pattern("XEX")
								.pattern(" X ")
								.define('X', Tags.Items.INGOTS_GOLD)
								.define('E', ModTags.SOURCE_GEM)
								.unlockedBy("has_source_gem", DataIngredient.tag(ModTags.SOURCE_GEM).getCritereon(p))
								.save(writer)
						)
						.generateAdvancement()
						.build(p, p.safeId(ctx.getEntry()));
			})
			.properties(props -> props.durability(Tiers.IRON.getUses()))
			.register();

	public static final ItemEntry<SuperiorShieldItem<IShieldType>> MANASTEEL_SHIELD = REGISTRATE.item("manasteel_shield", BotaniaCompat.makeBotaniaShieldOrDefault(BotaniaShield.MANASTEEL))
			.tab(() -> SuperiorShields.ITEM_GROUP)
			.tag(CURIOS_TAG)
			.tag(SHIELD_TAG)
			.recipe((ctx, p) -> {
				ConditionalRecipe.builder()
						.addCondition(new ModLoadedCondition("botania"))
						.addRecipe((writer) -> ShapedRecipeBuilder.shaped(ctx.getEntry())
								.pattern(" X ")
								.pattern("XEX")
								.pattern(" X ")
								.define('X', MANASTEEL_INGOT)
								.define('E', ModTags.MANAPEARL)
								.unlockedBy("has_manasteel", DataIngredient.tag(MANASTEEL_INGOT).getCritereon(p))
								.save(writer)
						)
						.generateAdvancement()
						.build(p, p.safeId(ctx.getEntry()));
			})
			.properties(props -> props.durability(300))
			.register();

	public static final ItemEntry<SuperiorShieldItem<IShieldType>> TERRASTEEL_SHIELD = REGISTRATE.item("terrasteel_shield", BotaniaCompat.makeBotaniaShieldOrDefault(BotaniaShield.TERRASTEEL))
			.tab(() -> SuperiorShields.ITEM_GROUP)
			.tag(CURIOS_TAG)
			.tag(SHIELD_TAG)
			.recipe((ctx, p) -> {
				ConditionalRecipe.builder()
						.addCondition(new ModLoadedCondition("botania"))
						.addRecipe((writer) -> ShapedRecipeBuilder.shaped(ctx.getEntry())
								.pattern(" X ")
								.pattern("XEX")
								.pattern(" X ")
								.define('X', TERRASTEEL_INGOT)
								.define('E', MANASTEEL_SHIELD.get())
								.unlockedBy("has_terrasteel", DataIngredient.tag(TERRASTEEL_INGOT).getCritereon(p))
								.save(writer)
						)
						.generateAdvancement()
						.build(p, p.safeId(ctx.getEntry()));
			})
			.properties(props -> props.durability(2300))
			.register();

	public static final ItemEntry<SuperiorShieldItem<IShieldType>> ELEMENTIUM_SHIELD = REGISTRATE.item("elementium_shield", BotaniaCompat.makeElementiumShieldOrDefault(BotaniaShield.ELEMENTIUM))
			.tab(() -> SuperiorShields.ITEM_GROUP)
			.tag(CURIOS_TAG)
			.tag(SHIELD_TAG)
			.recipe((ctx, p) -> {
				ConditionalRecipe.builder()
						.addCondition(new ModLoadedCondition("botania"))
						.addRecipe((writer) -> ShapedRecipeBuilder.shaped(ctx.getEntry())
								.pattern(" X ")
								.pattern("XEX")
								.pattern(" X ")
								.define('X', ELEMENTIUM_INGOT)
								.define('E', ModTags.PIXIE_DUST)
								.unlockedBy("has_elementium", DataIngredient.tag(ELEMENTIUM_INGOT).getCritereon(p))
								.save(writer)
						)
						.generateAdvancement()
						.build(p, p.safeId(ctx.getEntry()));
			})
			.properties(props -> props.durability(720))
			.register();

	// Mekanism Electric Shield
	public static final ItemEntry<EnergyShieldItem> ELECTRIC_SHIELD = REGISTRATE.item("electric_shield", props -> new EnergyShieldItem(props, EnergyShield.ELECTRIC_SHIELD))
			.tab(() -> SuperiorShields.ITEM_GROUP)
			.tag(CURIOS_TAG)
			.tag(SHIELD_TAG)
			.recipe((ctx, p) -> {
				ConditionalRecipe.builder()
						.addCondition(new ModLoadedCondition("mekanism"))
						.addRecipe((writer) -> ShapedRecipeBuilder.shaped(ctx.getEntry())
								.pattern(" X ")
								.pattern("XEX")
								.pattern(" X ")
								.define('X', ADVANCED_ALLOY)
								.define('E', ItemTags.create(new ResourceLocation("forge", "batteries")))
								.unlockedBy("has_advanced_alloy", DataIngredient.tag(ADVANCED_ALLOY).getCritereon(p))
								.save(writer)
						)
						.generateAdvancement()
						.build(p, p.safeId(ctx.getEntry()));
			})
			.register();

	// Thermal Augmentable Shield
	public static final ItemEntry<EnergyShieldItem> FLUX_SHIELD = REGISTRATE.item("flux_shield", ThermalCompat.makeThermalShieldOrDefault(AugmentableShield.BASE))
			.tab(() -> SuperiorShields.ITEM_GROUP)
			.tag(CURIOS_TAG)
			.tag(SHIELD_TAG)
			.recipe((ctx, p) -> {
				ConditionalRecipe.builder()
						.addCondition(new ModLoadedCondition("thermal"))
						.addRecipe((writer) -> ShapedRecipeBuilder.shaped(ctx.getEntry())
								.pattern(" G ")
								.pattern("XEX")
								.pattern(" G ")
								.define('G', TIN_GEAR)
								.define('X', Tags.Items.INGOTS_IRON)
								.define('E', ModTags.FLUX_COIL)
								.unlockedBy("has_redstone", DataIngredient.tag(Tags.Items.DUSTS_REDSTONE).getCritereon(p))
								.save(writer)
						)
						.generateAdvancement()
						.build(p, p.safeId(ctx.getEntry()));
			})
			.register();

	// Immersive Engineering Shield
	public static final ItemEntry<EnergyShieldItem> ENGINEERS_SHIELD = REGISTRATE.item("engineers_shield", props -> new EnergyShieldItem(props, EnergyShield.ENGINEERS_SHIELD))
			.tab(() -> SuperiorShields.ITEM_GROUP)
			.tag(CURIOS_TAG)
			.tag(SHIELD_TAG)
			.recipe((ctx, p) -> {
				ConditionalRecipe.builder()
						.addCondition(new ModLoadedCondition("immersiveengineering"))
						.addRecipe((writer) -> ShapedRecipeBuilder.shaped(ctx.getEntry())
								.pattern(" G ")
								.pattern("XEX")
								.pattern(" G ")
								.define('G', STEEL_INGOT)
								.define('X', ModTags.WOODEN_GRIP)
								.define('E', ModTags.COMPONENT_STEEL)
								.unlockedBy("has_steel", DataIngredient.tag(STEEL_INGOT).getCritereon(p))
								.save(writer)
						)
						.generateAdvancement()
						.build(p, p.safeId(ctx.getEntry()));
			})
			.lang("Engineer's Shield")
			.register();

	public static final ItemEntry<KnightmetalShieldItem> KNIGHTMETAL_SHIELD = REGISTRATE.item("knightmetal_shield", props -> new KnightmetalShieldItem(props, TwilightForestShield.KNIGHTMETAL))
			.tab(() -> SuperiorShields.ITEM_GROUP)
			.tag(CURIOS_TAG)
			.tag(SHIELD_TAG)
			.recipe((ctx, p) -> {
				var tag = ItemTags.create(new ResourceLocation("forge", "ingots/knightmetal"));
				ConditionalRecipe.builder()
						.addCondition(new NotCondition(
								new TagEmptyCondition(tag.location())
						))
						.addRecipe((writer) -> ShapedRecipeBuilder.shaped(ctx.getEntry())
								.pattern(" X ")
								.pattern("XEX")
								.pattern(" X ")
								.define('X', tag)
								.define('E', Items.ENDER_EYE)
								.unlockedBy("has_eye_of_ender", DataIngredient.items(Items.ENDER_EYE).getCritereon(p))
								.save(writer)
						)
						.generateAdvancement()
						.build(p, p.safeId(ctx.getEntry()));
			})
			.properties(props -> props.durability(512))
			.register();

	public static final ItemEntry<SuperiorShieldItem<IShieldType>> SOUL_STAINED_STEEL_SHIELD = REGISTRATE.item("soul_stained_steel_shield", MalumCompat.makeSoulStainedShieldOrDefault(MalumShield.SOUL_STAINED_STEEL))
			.tab(() -> SuperiorShields.ITEM_GROUP)
			.tag(CURIOS_TAG)
			.tag(SHIELD_TAG)
			.recipe((ctx, p) -> {
				ConditionalRecipe.builder()
						.addCondition(new ModLoadedCondition("malum"))
						.addRecipe((writer) -> ShapedRecipeBuilder.shaped(ctx.getEntry())
								.pattern(" X ")
								.pattern("XEX")
								.pattern(" X ")
								.define('X', ModTags.SOUL_STAINED_STEEL_INGOT)
								.define('E', ModTags.HALLOWED_SPIRIT_RESONATOR)
								.unlockedBy("has_eye_of_ender", DataIngredient.items(Items.ENDER_EYE).getCritereon(p))
								.save(writer)
						)
						.generateAdvancement()
						.build(p, p.safeId(ctx.getEntry()));
			})
			.properties(props -> props.durability(1250))
			.register();

	public static final ItemEntry<SuperiorShieldItem<IShieldType>> SPIRIT_HUNTER_SHIELD = REGISTRATE.item("spirit_hunter_shield", MalumCompat.makeSpiritHunterShieldOrDefault(MalumShield.SPIRIT_HUNTER))
			.tab(() -> SuperiorShields.ITEM_GROUP)
			.tag(CURIOS_TAG)
			.tag(SHIELD_TAG)
			.recipe((ctx, p) -> {
				ConditionalRecipe.builder()
						.addCondition(new ModLoadedCondition("malum"))
						.addRecipe((writer) -> ShapedRecipeBuilder.shaped(ctx.getEntry())
								.pattern(" X ")
								.pattern("XEX")
								.pattern(" X ")
								.define('X', ModTags.SPIRIT_FABRIC)
								.define('E', ModTags.STAINED_SPIRIT_RESONATOR)
								.unlockedBy("has_eye_of_ender", DataIngredient.items(Items.ENDER_EYE).getCritereon(p))
								.save(writer)
						)
						.generateAdvancement()
						.build(p, p.safeId(ctx.getEntry()));
			})
			.properties(props -> props.durability(200))
			.register();

	public static final ItemEntry<FieryShieldItem> FIERY_SHIELD = REGISTRATE.item("fiery_shield", props -> new FieryShieldItem(props, FieryShield.FIREY))
			.tab(() -> SuperiorShields.ITEM_GROUP)
			.tag(CURIOS_TAG)
			.tag(SHIELD_TAG)
			.recipe((ctx, p) -> {
				var tag = ItemTags.create(new ResourceLocation("forge", "ingots/fiery"));
				ConditionalRecipe.builder()
						.addCondition(new NotCondition(
								new TagEmptyCondition(tag.location())
						))
						.addRecipe((writer) -> ShapedRecipeBuilder.shaped(ctx.getEntry())
								.pattern(" X ")
								.pattern("XEX")
								.pattern(" X ")
								.define('X', tag)
								.define('E', Items.ENDER_EYE)
								.unlockedBy("has_eye_of_ender", DataIngredient.items(Items.ENDER_EYE).getCritereon(p))
								.save(writer)
						)
						.generateAdvancement()
						.build(p, p.safeId(ctx.getEntry()));
			})
			.properties(props -> props.durability(1024))
			.register();

	private static ItemBuilder<VanillaShieldItem, Registrate> vanillaShieldItem(IShieldType type, int durability) {
		return REGISTRATE.item(type.name().toLowerCase(Locale.ROOT) + "_shield", props -> new VanillaShieldItem(props, type)).tab(() -> SuperiorShields.ITEM_GROUP)
				.tag(CURIOS_TAG)
				.tag(SHIELD_TAG)
				.recipe((ctx, p) -> {
					var tag = ItemTags.create(new ResourceLocation("forge", "ingots/" + type.name().toLowerCase(Locale.ROOT)));
					ConditionalRecipe.builder()
							.addCondition(new NotCondition(
									new TagEmptyCondition(tag.location())
							))
							.addRecipe((writer) -> ShapedRecipeBuilder.shaped(ctx.getEntry())
									.pattern(" X ")
									.pattern("XEX")
									.pattern(" X ")
									.define('X', tag)
									.define('E', Tags.Items.ENDER_PEARLS)
									.unlockedBy("has_enderpearl", DataIngredient.tag(Tags.Items.ENDER_PEARLS).getCritereon(p))
									.save(writer)
							)
							.generateAdvancement()
							.build(p, p.safeId(ctx.getEntry()));
				})
				.properties(props -> props.durability(durability));
	}

	public static void classload() {
	}
}
