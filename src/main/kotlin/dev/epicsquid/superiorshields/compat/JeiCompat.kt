package dev.epicsquid.superiorshields.compat

import dev.epicsquid.squidink.utils.forgeTag
import dev.epicsquid.superiorshields.registry.ItemRegistry
import mezz.jei.api.IModPlugin
import mezz.jei.api.JeiPlugin
import mezz.jei.api.constants.VanillaTypes
import mezz.jei.api.registration.IRecipeRegistration
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.fml.ModList
import net.minecraftforge.registries.ForgeRegistries

@JeiPlugin
class JeiCompat : IModPlugin {

	companion object {
		fun classload() {}
	}

	override fun getPluginUid(): ResourceLocation = ResourceLocation("superiorshields", "jei")

	override fun registerRecipes(registration: IRecipeRegistration) {
		val malumShields = listOf(ItemRegistry.spiritHunterShield, ItemRegistry.soulStainedShield)
		val twilightForestShields = listOf(
			ItemRegistry.fieryShield,
			ItemRegistry.steeleafShield,
			ItemRegistry.knightmetalShield,
			ItemRegistry.ironwoodShield
		)
		val botaniaShields = listOf(
			ItemRegistry.manasteelShield,
			ItemRegistry.terrasteelShield,
			ItemRegistry.elementiumShield
		)
		val thermalShields = listOf(
			ItemRegistry.fluxShield
		)
		val immersiveEngineeringShields = listOf(
			ItemRegistry.engineersShield
		)
		val arsShields = listOf(
			ItemRegistry.enchanterShield
		)
		val mekShields = listOf(
			ItemRegistry.osmiumShield,
			ItemRegistry.refinedObsidianShield,
			ItemRegistry.refinedGlowstoneShield,
			ItemRegistry.lapisShield,
			ItemRegistry.electricShield
		)
		val enderIoShields = listOf(
			ItemRegistry.darksteelShield
		)

		if (!ModList.get().isLoaded("malum"))
			registration.ingredientManager.removeIngredientsAtRuntime(
				VanillaTypes.ITEM_STACK,
				malumShields.map { it.defaultInstance }
			)

		if (!ModList.get().isLoaded("twilightforest"))
			registration.ingredientManager.removeIngredientsAtRuntime(
				VanillaTypes.ITEM_STACK,
				twilightForestShields.map { it.defaultInstance }
			)

		if (!ModList.get().isLoaded("botania"))
			registration.ingredientManager.removeIngredientsAtRuntime(
				VanillaTypes.ITEM_STACK,
				botaniaShields.map { it.defaultInstance }
			)

		if (!ModList.get().isLoaded("thermal"))
			registration.ingredientManager.removeIngredientsAtRuntime(
				VanillaTypes.ITEM_STACK,
				thermalShields.map { it.defaultInstance }
			)

		if (!ModList.get().isLoaded("immersiveengineering"))
			registration.ingredientManager.removeIngredientsAtRuntime(
				VanillaTypes.ITEM_STACK,
				immersiveEngineeringShields.map { it.defaultInstance }
			)

		if (!ModList.get().isLoaded("ars_nouveau"))
			registration.ingredientManager.removeIngredientsAtRuntime(
				VanillaTypes.ITEM_STACK,
				arsShields.map { it.defaultInstance }
			)

		if (!ModList.get().isLoaded("mekanism"))
			registration.ingredientManager.removeIngredientsAtRuntime(
				VanillaTypes.ITEM_STACK,
				mekShields.map { it.defaultInstance }
			)

		if (!ModList.get().isLoaded("enderio"))
			registration.ingredientManager.removeIngredientsAtRuntime(
				VanillaTypes.ITEM_STACK,
				enderIoShields.map { it.defaultInstance }
			)

		ForgeRegistries.ITEMS.tags()?.getTag("ingots/tin".forgeTag)?.isEmpty?.let {
			if (it) registration.ingredientManager.removeIngredientsAtRuntime(
				VanillaTypes.ITEM_STACK,
				listOf(ItemRegistry.tinShield.defaultInstance)
			)
		}

		ForgeRegistries.ITEMS.tags()?.getTag("ingots/nickel".forgeTag)?.isEmpty?.let {
			if (it) registration.ingredientManager.removeIngredientsAtRuntime(
				VanillaTypes.ITEM_STACK,
				listOf(ItemRegistry.nickelShield.defaultInstance)
			)
		}

		ForgeRegistries.ITEMS.tags()?.getTag("ingots/bronze".forgeTag)?.isEmpty?.let {
			if (it) registration.ingredientManager.removeIngredientsAtRuntime(
				VanillaTypes.ITEM_STACK,
				listOf(ItemRegistry.bronzeShield.defaultInstance)
			)
		}

		ForgeRegistries.ITEMS.tags()?.getTag("ingots/electrum".forgeTag)?.isEmpty?.let {
			if (it) registration.ingredientManager.removeIngredientsAtRuntime(
				VanillaTypes.ITEM_STACK,
				listOf(ItemRegistry.electrumShield.defaultInstance)
			)
		}

		ForgeRegistries.ITEMS.tags()?.getTag("ingots/invar".forgeTag)?.isEmpty?.let {
			if (it) registration.ingredientManager.removeIngredientsAtRuntime(
				VanillaTypes.ITEM_STACK,
				listOf(ItemRegistry.invarShield.defaultInstance)
			)
		}

		ForgeRegistries.ITEMS.tags()?.getTag("ingots/lead".forgeTag)?.isEmpty?.let {
			if (it) registration.ingredientManager.removeIngredientsAtRuntime(
				VanillaTypes.ITEM_STACK,
				listOf(ItemRegistry.leadShield.defaultInstance)
			)
		}

		ForgeRegistries.ITEMS.tags()?.getTag("ingots/silver".forgeTag)?.isEmpty?.let {
			if (it) registration.ingredientManager.removeIngredientsAtRuntime(
				VanillaTypes.ITEM_STACK,
				listOf(ItemRegistry.silverShield.defaultInstance)
			)
		}

		ForgeRegistries.ITEMS.tags()?.getTag("ingots/steel".forgeTag)?.isEmpty?.let {
			if (it) registration.ingredientManager.removeIngredientsAtRuntime(
				VanillaTypes.ITEM_STACK,
				listOf(ItemRegistry.steelShield.defaultInstance)
			)
		}

		ForgeRegistries.ITEMS.tags()?.getTag("ingots/constantan".forgeTag)?.isEmpty?.let {
			if (it) registration.ingredientManager.removeIngredientsAtRuntime(
				VanillaTypes.ITEM_STACK,
				listOf(ItemRegistry.constantanShield.defaultInstance)
			)
		}

		ForgeRegistries.ITEMS.tags()?.getTag("ingots/electrum".forgeTag)?.isEmpty?.let {
			if (it) registration.ingredientManager.removeIngredientsAtRuntime(
				VanillaTypes.ITEM_STACK,
				listOf(ItemRegistry.electrumShield.defaultInstance)
			)
		}
	}
}
