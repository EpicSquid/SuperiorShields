package dev.epicsquid.superiorshields.data

import dev.epicsquid.superiorshields.SuperiorShields
import net.minecraft.core.HolderLookup.Provider
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.common.data.ExistingFileHelper
import top.theillusivec4.curios.api.CuriosDataProvider
import java.util.concurrent.CompletableFuture

class SuperiorShieldsCurios(
	output: PackOutput,
	fileHelper: ExistingFileHelper,
	registries: CompletableFuture<Provider>
) : CuriosDataProvider(SuperiorShields.MODID, output, fileHelper, registries) {

	companion object {
		const val SUPERIOR_SHIELD_CURIO = "superior_shield"
	}

	override fun generate(registries: Provider, fileHelper: ExistingFileHelper) {
		createSlot(SUPERIOR_SHIELD_CURIO)
			.icon(ResourceLocation(SuperiorShields.MODID, "item/empty_shield_slot"))
			.size(1)
		createEntities(SUPERIOR_SHIELD_CURIO)
			.addPlayer()
	}
}