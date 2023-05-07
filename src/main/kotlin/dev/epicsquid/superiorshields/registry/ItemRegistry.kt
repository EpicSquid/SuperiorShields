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
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.Tiers
import net.minecraft.world.level.ItemLike
import net.minecraftforge.common.Tags
import net.minecraftforge.common.crafting.ConditionalRecipe
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
			Tags.Items.GEMS_DIAMOND,
			centerItemOverride = Items.ENDER_EYE
		)
	}

	val netheriteShield: SuperiorShieldItem<DurabilitySuperiorShield> by registryEntry {
		durabilityShieldItem(
			"netherite_shield",
			Config.SHIELDS_CONFIG.netheriteShield,
			Tiers.NETHERITE.enchantmentValue,
			Tiers.NETHERITE.uses,
			Tags.Items.INGOTS_NETHERITE,
			centerItemOverride = Items.ENDER_EYE
		)
	}

	private fun durabilityShieldItem(
		name: String,
		stats: SuperiorShieldStats,
		enchantmentValue: Int,
		durability: Int,
		outerTag: TagKey<Item>,
		centerTag: TagKey<Item> = Tags.Items.ENDER_PEARLS,
		centerItemOverride: ItemLike? = null
	): ItemEntry<SuperiorShieldItem<DurabilitySuperiorShield>> =
		registrate.item<SuperiorShieldItem<DurabilitySuperiorShield>>(name) { props: Item.Properties ->
			SuperiorShieldItem(
				props,
				enchantmentValue,
				DurabilitySuperiorShield(name, stats)
			)
		}.tab { SuperiorShields.tab }
			.properties { it.durability(durability) }
			.tag(CURIOS_TAG)
			.tag(SUPERIOR_SHIELD_TAG)
			.recipe { ctx, p ->
				ConditionalRecipe.builder().apply {
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