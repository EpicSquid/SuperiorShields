package dev.epicsquid.superiorshields.data

import dev.epicsquid.squidink.utils.forgeTag
import dev.epicsquid.superiorshields.registry.ItemRegistry
import net.minecraft.advancements.critereon.InventoryChangeTrigger
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.*
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraftforge.common.Tags
import net.minecraftforge.common.crafting.ConditionalRecipe
import net.minecraftforge.common.crafting.conditions.ICondition
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition
import net.minecraftforge.common.crafting.conditions.NotCondition
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition
import net.minecraftforge.registries.ForgeRegistries
import java.util.function.Consumer

class SuperiorShieldsRecipes(output: PackOutput) : RecipeProvider(output) {

	override fun buildRecipes(writer: Consumer<FinishedRecipe>) {
		shapedShieldRecipe(writer, ItemRegistry.ironShield, Tags.Items.INGOTS_IRON)
		shapedShieldRecipe(writer, ItemRegistry.goldenShield, Tags.Items.INGOTS_GOLD)
		shapedShieldRecipe(writer, ItemRegistry.diamondShield, Tags.Items.GEMS_DIAMOND)
		shapedShieldRecipe(writer, ItemRegistry.copperShield, Tags.Items.INGOTS_COPPER)
		shapedShieldRecipe(writer, ItemRegistry.silverShield, "ingots/silver".forgeTag)
		shapedShieldRecipe(writer, ItemRegistry.leadShield, "ingots/lead".forgeTag)
		shapedShieldRecipe(writer, ItemRegistry.tinShield, "ingots/tin".forgeTag)
		shapedShieldRecipe(writer, ItemRegistry.bronzeShield, "ingots/bronze".forgeTag)
		shapedShieldRecipe(writer, ItemRegistry.steelShield, "ingots/steel".forgeTag)
		shapedShieldRecipe(writer, ItemRegistry.electrumShield, "ingots/electrum".forgeTag)
		shapedShieldRecipe(writer, ItemRegistry.nickelShield, "ingots/nickel".forgeTag)
		shapedShieldRecipe(writer, ItemRegistry.invarShield, "ingots/invar".forgeTag)
		shapedShieldRecipe(writer, ItemRegistry.constantanShield, "ingots/constantan".forgeTag)
		shapedShieldRecipe(writer, ItemRegistry.osmiumShield, "ingots/osmium".forgeTag)
		shapedShieldRecipe(writer, ItemRegistry.refinedObsidianShield, "ingots/refined_obsidian".forgeTag)
		shapedShieldRecipe(writer, ItemRegistry.refinedGlowstoneShield, "ingots/refined_glowstone".forgeTag)
		shapedShieldRecipe(
			writer = writer,
			item = ItemRegistry.lapisShield,
			outerTag = Tags.Items.GEMS_LAPIS,
			conditions = arrayOf(ModLoadedCondition("mekanism"))
		)
		shapedShieldRecipe(writer, ItemRegistry.knightmetalShield, "ingots/knightmetal".forgeTag)
		shapedShieldRecipe(writer, ItemRegistry.ironwoodShield, "ingots/ironwood".forgeTag)
		shapedShieldRecipe(writer, ItemRegistry.steeleafShield, "ingots/steeleaf".forgeTag)
		shapedShieldRecipe(writer, ItemRegistry.fieryShield, "ingots/fiery".forgeTag)
		SmithingTransformRecipeBuilder
			.smithing(
				Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
				Ingredient.of(ItemRegistry.diamondShield),
				Ingredient.of(Tags.Items.INGOTS_NETHERITE),
				RecipeCategory.MISC,
				ItemRegistry.netheriteShield
			).unlocks(
				"has_smithing_trim_template", InventoryChangeTrigger.TriggerInstance.hasItems(
					ItemPredicate.Builder.item().of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE).build()
				)
			).save(writer, ForgeRegistries.ITEMS.getKey(ItemRegistry.netheriteShield)!!)
		shapedShieldRecipe(
			writer,
			ItemRegistry.electricShield,
			"alloys/advanced".forgeTag,
			Ingredient.of("batteries".forgeTag),
			NotCondition(TagEmptyCondition("batteries".forgeTag.location)),
		)

		ConditionalRecipe.builder().apply {
			addCondition(ModLoadedCondition("immersiveengineering"))
			addCondition(NotCondition(TagEmptyCondition(SuperiorShieldsItemTags.WOODEN_GRIP.location)))
			addCondition(NotCondition(TagEmptyCondition(SuperiorShieldsItemTags.COMPONENT_STEEL.location)))
			addCondition(NotCondition(TagEmptyCondition("ingots/steel".forgeTag.location)))
			addRecipe { writer ->
				ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.engineersShield).apply {
					pattern(" G ")
					pattern("XEX")
					pattern(" G ")
					define('G', "ingots/steel".forgeTag)
					define('X', SuperiorShieldsItemTags.WOODEN_GRIP)
					define('E', SuperiorShieldsItemTags.COMPONENT_STEEL)
					unlockedBy(
						"has_item", InventoryChangeTrigger.TriggerInstance.hasItems(
							ItemPredicate.Builder.item().of(SuperiorShieldsItemTags.COMPONENT_STEEL).build()
						)
					)
					save(writer)
				}
			}
			generateAdvancement()
		}.build(writer, ForgeRegistries.ITEMS.getKey(ItemRegistry.engineersShield)!!)

		ConditionalRecipe.builder().apply {
			addCondition(ModLoadedCondition("thermal"))
			addCondition(NotCondition(TagEmptyCondition("gears/tin".forgeTag.location)))
			addCondition(NotCondition(TagEmptyCondition(SuperiorShieldsItemTags.FLUX_COIL.location)))
			addRecipe { writer ->
				ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.fluxShield).apply {
					pattern(" G ")
					pattern("XEX")
					pattern(" G ")
					define('G', "gears/tin".forgeTag)
					define('X', Tags.Items.INGOTS_IRON)
					define('E', SuperiorShieldsItemTags.FLUX_COIL)
					unlockedBy(
						"has_item", InventoryChangeTrigger.TriggerInstance.hasItems(
							ItemPredicate.Builder.item().of(Tags.Items.DUSTS_REDSTONE).build()
						)
					)
					save(writer)
				}
			}
			generateAdvancement()
		}.build(writer, ForgeRegistries.ITEMS.getKey(ItemRegistry.fluxShield)!!)

		shapedShieldRecipe(
			writer = writer,
			item = ItemRegistry.manasteelShield,
			outerTag = "ingots/manasteel".forgeTag,
			centerIngredient = Ingredient.of(SuperiorShieldsItemTags.MANAPEARL)
		)
		shapedShieldRecipe(
			writer = writer,
			item = ItemRegistry.terrasteelShield,
			outerTag = "ingots/terrasteel".forgeTag,
			centerIngredient = Ingredient.of(SuperiorShieldsItemTags.MANAPEARL)
		)
		shapedShieldRecipe(
			writer = writer,
			item = ItemRegistry.elementiumShield,
			outerTag = "ingots/elementium".forgeTag,
			centerIngredient = Ingredient.of(SuperiorShieldsItemTags.PIXIE_DUST)
		)
		shapedShieldRecipe(
			writer = writer,
			item = ItemRegistry.soulStainedShield,
			outerTag = SuperiorShieldsItemTags.SOUL_STAINED_STEEL_INGOT,
			centerIngredient = Ingredient.of(SuperiorShieldsItemTags.SPECTRAL_LENS)
		)
		shapedShieldRecipe(
			writer = writer,
			item = ItemRegistry.spiritHunterShield,
			outerTag = SuperiorShieldsItemTags.SPIRIT_FABRIC,
			centerIngredient = Ingredient.of(SuperiorShieldsItemTags.SPECTRAL_LENS)
		)
		shapedShieldRecipe(
			writer = writer,
			item = ItemRegistry.enchanterShield,
			outerTag = Tags.Items.INGOTS_GOLD,
			centerIngredient = Ingredient.of(SuperiorShieldsItemTags.SOURCE_GEM)
		)
	}

	private fun shapedShieldRecipe(
		writer: Consumer<FinishedRecipe>,
		item: Item,
		outerTag: TagKey<Item>,
		centerIngredient: Ingredient = Ingredient.of(Tags.Items.ENDER_PEARLS),
		vararg conditions: ICondition
	) {
		ConditionalRecipe.builder().apply {
			conditions.forEach(::addCondition)
			addCondition(NotCondition(TagEmptyCondition(outerTag.location)))
			addRecipe { writer ->
				ShapedRecipeBuilder.shaped(RecipeCategory.MISC, item).apply {
					pattern(" X ")
					pattern("XEX")
					pattern(" X ")
					define('X', outerTag)
					define('E', centerIngredient)
					unlockedBy(
						"has_item", InventoryChangeTrigger.TriggerInstance.hasItems(
							ItemPredicate.Builder.item().of(outerTag).build()
						)
					)
					save(writer)
				}
			}
			generateAdvancement()
		}.build(writer, ForgeRegistries.ITEMS.getKey(item)!!)
	}
}