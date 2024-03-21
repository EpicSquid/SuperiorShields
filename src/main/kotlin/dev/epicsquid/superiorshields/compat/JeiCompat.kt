package dev.epicsquid.superiorshields.compat

import dev.epicsquid.superiorshields.registry.ItemRegistry
import mezz.jei.api.IModPlugin
import mezz.jei.api.JeiPlugin
import mezz.jei.api.constants.VanillaTypes
import mezz.jei.api.registration.IRecipeRegistration
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.fml.ModList

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
	}
}
