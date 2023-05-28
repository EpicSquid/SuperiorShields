package dev.epicsquid.superiorshields.registry

import com.tterrag.registrate.util.DataIngredient
import com.tterrag.registrate.util.entry.ItemEntry
import dev.epicsquid.superiorshields.SuperiorShields
import dev.epicsquid.superiorshields.config.Config
import dev.epicsquid.superiorshields.config.SuperiorShieldStats
import dev.epicsquid.superiorshields.item.SuperiorShieldItem
import dev.epicsquid.superiorshields.shield.DurabilitySuperiorShield
import dev.epicsquid.superiorshields.utils.registryEntry
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.UpgradeRecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.Tiers
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.minecraftforge.common.Tags
import net.minecraftforge.common.crafting.ConditionalRecipe
import net.minecraftforge.common.crafting.conditions.ICondition
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition
import net.minecraftforge.common.crafting.conditions.NotCondition
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition
import top.theillusivec4.curios.api.CuriosApi

object ItemRegistry {

	val registrate = SuperiorShields.registrate

	private val CURIOS_TAG =
		ItemTags.create(ResourceLocation(CuriosApi.MODID, SuperiorShields.SUPERIOR_SHIELD_CURIO))
	private val SUPERIOR_SHIELD_TAG =
		ItemTags.create(ResourceLocation(SuperiorShields.MODID, "shield"))

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
			.tag(CURIOS_TAG)
			.tag(SUPERIOR_SHIELD_TAG)
			.recipe { ctx, p ->
				UpgradeRecipeBuilder
					.smithing(Ingredient.of(diamondShield), Ingredient.of(Tags.Items.INGOTS_NETHERITE), ctx.get())
					.unlocks("has_netherite", DataIngredient.tag(Tags.Items.INGOTS_NETHERITE).getCritereon(p))
					.save(p, p.safeId(ctx.get()))
			}
			.register()
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
			.tag(CURIOS_TAG)
			.tag(SUPERIOR_SHIELD_TAG)
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