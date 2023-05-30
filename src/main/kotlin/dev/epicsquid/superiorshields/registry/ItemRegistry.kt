package dev.epicsquid.superiorshields.registry

import com.tterrag.registrate.util.DataIngredient
import com.tterrag.registrate.util.entry.ItemEntry
import dev.epicsquid.superiorshields.SuperiorShields
import dev.epicsquid.superiorshields.compat.ArsCompat
import dev.epicsquid.superiorshields.compat.BotaniaCompat
import dev.epicsquid.superiorshields.compat.MalumCompat
import dev.epicsquid.superiorshields.compat.ThermalCompat
import dev.epicsquid.superiorshields.config.Config
import dev.epicsquid.superiorshields.config.SuperiorShieldStats
import dev.epicsquid.superiorshields.data.SuperiorShieldsTags
import dev.epicsquid.superiorshields.item.EnergySuperiorShieldItem
import dev.epicsquid.superiorshields.item.SuperiorShieldItem
import dev.epicsquid.superiorshields.item.ThermalSuperiorShieldItem
import dev.epicsquid.superiorshields.shield.DurabilitySuperiorShield
import dev.epicsquid.superiorshields.shield.EnergySuperiorShield
import dev.epicsquid.superiorshields.shield.ThermalSuperiorShield
import dev.epicsquid.superiorshields.utils.registryEntry
import epicsquid.superiorshields.item.ThermalShieldItem
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.UpgradeRecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Tiers
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.minecraftforge.common.Tags
import net.minecraftforge.common.crafting.ConditionalRecipe
import net.minecraftforge.common.crafting.conditions.ICondition
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition
import net.minecraftforge.common.crafting.conditions.NotCondition
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition

object ItemRegistry {

	val registrate = SuperiorShields.registrate

	val ironShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		durabilityShieldItem(
			"iron_shield",
			Config.SHIELDS_CONFIG.ironShield,
			Tiers.IRON.enchantmentValue,
			Tiers.IRON.uses,
			Tags.Items.INGOTS_IRON
		)
	}

	val goldShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		durabilityShieldItem(
			"golden_shield",
			Config.SHIELDS_CONFIG.goldShield,
			Tiers.GOLD.enchantmentValue,
			Tiers.GOLD.uses,
			Tags.Items.INGOTS_GOLD
		)
	}

	val copperShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		durabilityShieldItem(
			"copper_shield",
			Config.SHIELDS_CONFIG.copperShield,
			9,
			160,
			Tags.Items.INGOTS_COPPER
		)
	}

	val diamondShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		durabilityShieldItem(
			"diamond_shield",
			Config.SHIELDS_CONFIG.diamondShield,
			Tiers.DIAMOND.enchantmentValue,
			Tiers.DIAMOND.uses,
			Tags.Items.GEMS_DIAMOND
		)
	}

	// Metal Shields
	val tinShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		durabilityShieldItem(
			"tin_shield",
			Config.SHIELDS_CONFIG.tinShield,
			18,
			32,
			"ingots/tin".forgeTag
		)
	}

	val leadShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		durabilityShieldItem(
			"lead_shield",
			Config.SHIELDS_CONFIG.leadShield, 16,
			64,
			"ingots/lead".forgeTag
		)
	}

	val silverShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		durabilityShieldItem(
			"silver_shield",
			Config.SHIELDS_CONFIG.silverShield,
			30,
			48,
			"ingots/silver".forgeTag
		)
	}

	val nickelShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		durabilityShieldItem(
			"nickel_shield",
			Config.SHIELDS_CONFIG.nickelShield,
			30,
			240,
			"ingots/nickel".forgeTag
		)
	}

	val bronzeShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		durabilityShieldItem(
			"bronze_shield",
			Config.SHIELDS_CONFIG.bronzeShield,
			16,
			325,
			"ingots/bronze".forgeTag
		)
	}

	val electrumShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		durabilityShieldItem(
			"electrum_shield",
			Config.SHIELDS_CONFIG.electrumShield,
			28,
			192,
			"ingots/electrum".forgeTag
		)
	}

	val invarShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		durabilityShieldItem(
			"invar_shield",
			Config.SHIELDS_CONFIG.invarShield,
			13,
			300,
			"ingots/invar".forgeTag
		)
	}

	val constantanShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		durabilityShieldItem(
			"constantan_shield",
			Config.SHIELDS_CONFIG.constantanShield,
			10,
			250,
			"ingots/constantan".forgeTag
		)
	}

	val lapisShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		durabilityShieldItem(
			"lapis_shield",
			Config.SHIELDS_CONFIG.lapisShield,
			32,
			128,
			"gem/lapis".forgeTag,
			conditions = listOf(ModLoadedCondition("mekanism"))
		)
	}

	val steelShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		durabilityShieldItem(
			"steel_shield",
			Config.SHIELDS_CONFIG.steelShield,
			16,
			500,
			"ingots/steel".forgeTag
		)
	}

	val osmiumShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		durabilityShieldItem(
			"osmium_shield",
			Config.SHIELDS_CONFIG.osmiumShield,
			14,
			250,
			"ingots/osmium".forgeTag
		)
	}

	val refinedGlowstoneShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		durabilityShieldItem(
			"refined_glowstone_shield",
			Config.SHIELDS_CONFIG.refinedGlowstoneShield,
			20,
			384,
			"ingots/refined_glowstone".forgeTag
		)
	}

	val refinedObsidianShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		durabilityShieldItem(
			"refined_obsidian_shield",
			Config.SHIELDS_CONFIG.refinedObsidianShield,
			18,
			1680,
			"ingots/refined_obsidian".forgeTag
		)
	}

	val netheriteShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		registrate.item<SuperiorShieldItem<DurabilitySuperiorShield>>("netherite_shield") { props: Item.Properties ->
			SuperiorShieldItem(
				props,
				Tiers.NETHERITE.enchantmentValue,
				DurabilitySuperiorShield("netherite_shield", Config.SHIELDS_CONFIG.netheriteShield)
			) { Ingredient.of(Tags.Items.INGOTS_NETHERITE) }
		}
			.properties { it.durability(Tiers.NETHERITE.uses) }
			.tag(SuperiorShieldsTags.CURIOS_TAG)
			.tag(SuperiorShieldsTags.SHIELD_TAG)
			.recipe { ctx, p ->
				UpgradeRecipeBuilder
					.smithing(Ingredient.of(diamondShield), Ingredient.of(Tags.Items.INGOTS_NETHERITE), ctx.get())
					.unlocks("has_netherite", DataIngredient.tag(Tags.Items.INGOTS_NETHERITE).getCritereon(p))
					.save(p, p.safeId(ctx.get()))
			}
			.register()
	}

	val electricShield: EnergySuperiorShieldItem<EnergySuperiorShield> by registryEntry {
		registrate.item<EnergySuperiorShieldItem<EnergySuperiorShield>>("electric_shield") { props: Item.Properties ->
			EnergySuperiorShieldItem(
				props = props,
				enchantmentValue = 12,
				type = EnergySuperiorShield("electric_shield", Config.SHIELDS_CONFIG.electricShield),
				maxEnergy = 48000,
				barColor = 0x3CFE9A
			)
		}
			.tag(SuperiorShieldsTags.CURIOS_TAG)
			.tag(SuperiorShieldsTags.SHIELD_TAG)
			.recipe { ctx, p ->
				ConditionalRecipe.builder().apply {
					addCondition(ModLoadedCondition("mekanism"))
					addRecipe { writer ->
						ShapedRecipeBuilder.shaped(ctx.entry).apply {
							pattern(" X ")
							pattern("XEX")
							pattern(" X ")
							define('X', SuperiorShieldsTags.ADVANCED_ALLOY)
							define('E', ItemTags.create(ResourceLocation("forge", "batteries")))
							unlockedBy("has_item", DataIngredient.tag(SuperiorShieldsTags.ADVANCED_ALLOY).getCritereon(p))
							save(writer)
						}
					}
					generateAdvancement()
				}.build(p, p.safeId(ctx.entry))
			}
			.register()
	}

	val engineersShield: EnergySuperiorShieldItem<EnergySuperiorShield> by registryEntry {
		registrate.item<EnergySuperiorShieldItem<EnergySuperiorShield>>("engineers_shield") { props: Item.Properties ->
			EnergySuperiorShieldItem(
				props = props,
				enchantmentValue = 14,
				type = EnergySuperiorShield("engineers_shield", Config.SHIELDS_CONFIG.engineersShield),
				maxEnergy = 48000,
				barColor = 0xFF0000
			)
		}
			.tag(SuperiorShieldsTags.CURIOS_TAG)
			.tag(SuperiorShieldsTags.SHIELD_TAG)
			.recipe { ctx, p ->
				ConditionalRecipe.builder().apply {
					addCondition(ModLoadedCondition("immersiveengineering"))
					addRecipe { writer ->
						ShapedRecipeBuilder.shaped(ctx.entry).apply {
							pattern(" G ")
							pattern("XEX")
							pattern(" G ")
							define('G', "ingots/steel".forgeTag)
							define('X', SuperiorShieldsTags.WOODEN_GRIP)
							define('E', SuperiorShieldsTags.COMPONENT_STEEL)
							unlockedBy("has_item", DataIngredient.tag("ingots/steel".forgeTag).getCritereon(p))
							save(writer)
						}
					}
					generateAdvancement()
				}.build(p, p.safeId(ctx.entry))
			}
			.register()
	}

	val fluxShield: EnergySuperiorShieldItem<*> by registryEntry {
		registrate.item(
			"flux_shield", ThermalCompat.thermalItemBuilder(
				enchantmentValue = 14,
				type = EnergySuperiorShield("flux_shield", Config.SHIELDS_CONFIG.fluxShield),
				maxEnergy = 48000,
				barColor = 0xFF0000
			)
		)
			.tag(SuperiorShieldsTags.CURIOS_TAG)
			.tag(SuperiorShieldsTags.SHIELD_TAG)
			.recipe { ctx, p ->
				ConditionalRecipe.builder().apply {
					addCondition(ModLoadedCondition("thermal"))
					addRecipe { writer ->
						ShapedRecipeBuilder.shaped(ctx.entry).apply {
							pattern(" G ")
							pattern("XEX")
							pattern(" G ")
							define('G', SuperiorShieldsTags.TIN_GEAR)
							define('X', Tags.Items.INGOTS_IRON)
							define('E', SuperiorShieldsTags.FLUX_COIL)
							unlockedBy("has_item", DataIngredient.tag(Tags.Items.DUSTS_REDSTONE).getCritereon(p))
							save(writer)
						}
					}
					generateAdvancement()
				}.build(p, p.safeId(ctx.entry))
			}
			.register()
	}

	val manasteelShield: SuperiorShieldItem<*> by registryEntry {
		registrate.item("manasteel_shield", BotaniaCompat.botaniaManaShieldBuilder(
			enchantmentValue = 16,
			type = DurabilitySuperiorShield("manasteel_shield", Config.SHIELDS_CONFIG.manasteelShield),
			repairItem = { Ingredient.of(SuperiorShieldsTags.MANASTEEL_INGOT) }
		))
			.properties { it.durability(300) }
			.tag(SuperiorShieldsTags.CURIOS_TAG)
			.tag(SuperiorShieldsTags.SHIELD_TAG)
			.recipe { ctx, p ->
				ConditionalRecipe.builder().apply {
					addCondition(ModLoadedCondition("botania"))
					addRecipe { writer ->
						ShapedRecipeBuilder.shaped(ctx.entry).apply {
							pattern(" X ")
							pattern("XEX")
							pattern(" X ")
							define('X', SuperiorShieldsTags.MANASTEEL_INGOT)
							define('E', SuperiorShieldsTags.MANAPEARL)
							unlockedBy("has_item", DataIngredient.tag(SuperiorShieldsTags.MANASTEEL_INGOT).getCritereon(p))
							save(writer)
						}
					}
					generateAdvancement()
				}.build(p, p.safeId(ctx.entry))
			}.register()
	}

	val terrasteelShield: SuperiorShieldItem<*> by registryEntry {
		registrate.item("terrasteel_shield", BotaniaCompat.botaniaManaShieldBuilder(
			enchantmentValue = 18,
			type = DurabilitySuperiorShield("terrasteel_shield", Config.SHIELDS_CONFIG.terrasteelShield),
			repairItem = { Ingredient.of(SuperiorShieldsTags.TERRASTEEL_INGOT) }
		))
			.properties { it.durability(600) }
			.tag(SuperiorShieldsTags.CURIOS_TAG)
			.tag(SuperiorShieldsTags.SHIELD_TAG)
			.recipe { ctx, p ->
				ConditionalRecipe.builder().apply {
					addCondition(ModLoadedCondition("botania"))
					addRecipe { writer ->
						ShapedRecipeBuilder.shaped(ctx.entry).apply {
							pattern(" X ")
							pattern("XEX")
							pattern(" X ")
							define('X', SuperiorShieldsTags.TERRASTEEL_INGOT)
							define('E', SuperiorShieldsTags.MANAPEARL)
							unlockedBy("has_item", DataIngredient.tag(SuperiorShieldsTags.TERRASTEEL_INGOT).getCritereon(p))
							save(writer)
						}
					}
					generateAdvancement()
				}.build(p, p.safeId(ctx.entry))
			}.register()
	}

	val elementiumShield: SuperiorShieldItem<*> by registryEntry {
		registrate.item("elementium_shield", BotaniaCompat.elementiumShieldBuilder(
			enchantmentValue = 20,
			type = DurabilitySuperiorShield("elementium_shield", Config.SHIELDS_CONFIG.elementiumShield),
			repairItem = { Ingredient.of(SuperiorShieldsTags.ELEMENTIUM_INGOT) }
		))
			.properties { it.durability(900) }
			.tag(SuperiorShieldsTags.CURIOS_TAG)
			.tag(SuperiorShieldsTags.SHIELD_TAG)
			.recipe { ctx, p ->
				ConditionalRecipe.builder().apply {
					addCondition(ModLoadedCondition("botania"))
					addRecipe { writer ->
						ShapedRecipeBuilder.shaped(ctx.entry).apply {
							pattern(" X ")
							pattern("XEX")
							pattern(" X ")
							define('X', SuperiorShieldsTags.ELEMENTIUM_INGOT)
							define('E', SuperiorShieldsTags.PIXIE_DUST)
							unlockedBy("has_item", DataIngredient.tag(SuperiorShieldsTags.ELEMENTIUM_INGOT).getCritereon(p))
							save(writer)
						}
					}
					generateAdvancement()
				}.build(p, p.safeId(ctx.entry))
			}.register()
	}

	val soulStainedShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		registrate.item("soul_stained_shield", MalumCompat.soulStainedShieldBuilder(
			enchantmentValue = 11,
			type = DurabilitySuperiorShield("soul_stained_shield", Config.SHIELDS_CONFIG.soulStainedShield),
			repairItem = { Ingredient.of(SuperiorShieldsTags.SOUL_STAINED_STEEL_INGOT) }
		))
			.properties { it.durability(1250) }
			.tag(SuperiorShieldsTags.CURIOS_TAG)
			.tag(SuperiorShieldsTags.SHIELD_TAG)
			.recipe { ctx, p ->
				ConditionalRecipe.builder().apply {
					addCondition(ModLoadedCondition("malum"))
					addRecipe { writer ->
						ShapedRecipeBuilder.shaped(ctx.entry).apply {
							pattern(" X ")
							pattern("XEX")
							pattern(" X ")
							define('X', SuperiorShieldsTags.SOUL_STAINED_STEEL_INGOT)
							define('E', SuperiorShieldsTags.HALLOWED_SPIRIT_RESONATOR)
							unlockedBy("has_item", DataIngredient.tag(SuperiorShieldsTags.SOUL_STAINED_STEEL_INGOT).getCritereon(p))
							save(writer)
						}
					}
					generateAdvancement()
				}.build(p, p.safeId(ctx.entry))
			}.register()
	}

	val spiritHunterShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		registrate.item("spirit_hunter_shield", MalumCompat.spiritHunterShieldBuilder(
			enchantmentValue = 15,
			type = DurabilitySuperiorShield("spirit_hunter_shield", Config.SHIELDS_CONFIG.spiritHunterShield),
			repairItem = { Ingredient.of(SuperiorShieldsTags.SPIRIT_FABRIC) }
		))
			.properties { it.durability(200) }
			.tag(SuperiorShieldsTags.CURIOS_TAG)
			.tag(SuperiorShieldsTags.SHIELD_TAG)
			.recipe { ctx, p ->
				ConditionalRecipe.builder().apply {
					addCondition(ModLoadedCondition("malum"))
					addRecipe { writer ->
						ShapedRecipeBuilder.shaped(ctx.entry).apply {
							pattern(" X ")
							pattern("XEX")
							pattern(" X ")
							define('X', SuperiorShieldsTags.SPIRIT_FABRIC)
							define('E', SuperiorShieldsTags.STAINED_SPIRIT_RESONATOR)
							unlockedBy("has_item", DataIngredient.tag(SuperiorShieldsTags.SPIRIT_FABRIC).getCritereon(p))
							save(writer)
						}
					}
					generateAdvancement()
				}.build(p, p.safeId(ctx.entry))
			}.register()
	}

	val enchanterShield: SuperiorShieldItem<*> by registryEntry {
		registrate.item("enchanter_shield", ArsCompat.arsManaShieldBuilder(
			enchantmentValue = 24,
			type = DurabilitySuperiorShield("enchanter_shield", Config.SHIELDS_CONFIG.enchanterShield),
			repairItem = { Ingredient.of(SuperiorShieldsTags.SOURCE_GEM) }
		))
			.properties {it.durability(Tiers.IRON.uses)}
			.tag(SuperiorShieldsTags.CURIOS_TAG)
			.tag(SuperiorShieldsTags.SHIELD_TAG)
			.recipe { ctx, p ->
				ConditionalRecipe.builder().apply {
					addCondition(ModLoadedCondition("ars_nouveau"))
					addRecipe { writer ->
						ShapedRecipeBuilder.shaped(ctx.entry).apply {
							pattern(" X ")
							pattern("XEX")
							pattern(" X ")
							define('X', Tags.Items.INGOTS_GOLD)
							define('E', SuperiorShieldsTags.SOURCE_GEM)
							unlockedBy("has_item", DataIngredient.tag(SuperiorShieldsTags.SOURCE_GEM).getCritereon(p))
							save(writer)
						}
					}
					generateAdvancement()
				}.build(p, p.safeId(ctx.entry))
			}.register()
	}

	private fun durabilityShieldItem(
		name: String,
		stats: SuperiorShieldStats,
		enchantmentValue: Int,
		durability: Int,
		outerTag: TagKey<Item>,
		centerTag: TagKey<Item> = Tags.Items.ENDER_PEARLS,
		centerItemOverride: ItemLike? = null,
		conditions: List<ICondition> = emptyList()
	): ItemEntry<SuperiorShieldItem<DurabilitySuperiorShield>> =
		registrate.item<SuperiorShieldItem<DurabilitySuperiorShield>>(name) { props: Item.Properties ->
			SuperiorShieldItem(
				props,
				enchantmentValue,
				DurabilitySuperiorShield(name, stats)
			) { Ingredient.of(outerTag) }
		}
			.properties { it.durability(durability) }
			.tag(SuperiorShieldsTags.CURIOS_TAG)
			.tag(SuperiorShieldsTags.SHIELD_TAG)
			.recipe { ctx, p ->
				ConditionalRecipe.builder().apply {
					conditions.forEach(::addCondition)
					addCondition(NotCondition(TagEmptyCondition(outerTag.location)))
					addRecipe { writer ->
						ShapedRecipeBuilder.shaped(ctx.entry).apply {
							pattern(" X ")
							pattern("XEX")
							pattern(" X ")
							define('X', outerTag)
							if (centerItemOverride != null)
								define('E', centerItemOverride)
							else
								define('E', centerTag)
							unlockedBy("has_item", DataIngredient.tag(centerTag).getCritereon(p))
							save(writer)
						}
					}
					generateAdvancement()
				}.build(p, p.safeId(ctx.entry))
			}.register()

	private val String.forgeTag: TagKey<Item>
		get() = ItemTags.create(ResourceLocation("forge", this))

	fun classload() {}
}