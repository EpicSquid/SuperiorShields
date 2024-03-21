package dev.epicsquid.superiorshields.data

import dev.epicsquid.superiorshields.SuperiorShields
import dev.epicsquid.superiorshields.item.SuperiorShieldItem
import dev.epicsquid.superiorshields.registry.ItemRegistry
import dev.epicsquid.superiorshields.utils.forgeTag
import net.minecraft.core.HolderLookup.Provider
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraftforge.common.data.BlockTagsProvider
import net.minecraftforge.common.data.ExistingFileHelper
import top.theillusivec4.curios.api.CuriosApi
import java.util.concurrent.CompletableFuture

class SuperiorShieldsItemTags(
	output: PackOutput,
	lookupProvider: CompletableFuture<Provider>,
	blockTagsProvider: BlockTagsProvider,
	existingFileHelper: ExistingFileHelper?
) : ItemTagsProvider(
	output,
	lookupProvider,
	blockTagsProvider.contentsGetter(),
	SuperiorShields.MODID,
	existingFileHelper
) {
	companion object {
		val CURIOS_TAG: TagKey<Item> =
			ItemTags.create(ResourceLocation(CuriosApi.MODID, SuperiorShieldsCurios.SUPERIOR_SHIELD_CURIO))

		val MANAPEARL: TagKey<Item> = "mana_pearl".forgeTag
		val PIXIE_DUST: TagKey<Item> = "pixie_dust".forgeTag
		val FLUX_COIL: TagKey<Item> = ItemTags.create(ResourceLocation("thermal", "flux_coil"))
		val COMPONENT_STEEL: TagKey<Item> = "component_steel".forgeTag
		val WOODEN_GRIP: TagKey<Item> = "wooden_grip".forgeTag
		val SOUL_STAINED_STEEL_INGOT: TagKey<Item> = "soul_stained_steel_ingot".forgeTag
		val SPIRIT_FABRIC: TagKey<Item> = ItemTags.create(ResourceLocation("malum", "spirit_fabric"))
		val SPECTRAL_LENS: TagKey<Item> = ItemTags.create(ResourceLocation("malum", "spectral_lens"))
		val SOURCE_GEM: TagKey<Item> = "gem/source".forgeTag
	}

	override fun addTags(provider: Provider) {
		tag(MANAPEARL)
			.addOptional(ResourceLocation("botania", "mana_pearl"))
		tag(PIXIE_DUST)
			.addOptional(ResourceLocation("botania", "pixie_dust"))
		tag(FLUX_COIL)
			.addOptional(ResourceLocation("thermal", "rf_coil"))
		tag(COMPONENT_STEEL)
			.addOptional(ResourceLocation("immersiveengineering", "component_steel"))
		tag(WOODEN_GRIP)
			.addOptional(ResourceLocation("immersiveengineering", "wooden_grip"))
		tag(SOUL_STAINED_STEEL_INGOT)
			.addOptional(ResourceLocation("malum", "soul_stained_steel_ingot"))
		tag(SPIRIT_FABRIC)
			.addOptional(ResourceLocation("malum", "spirit_fabric"))
		tag(SPECTRAL_LENS)
			.addOptional(ResourceLocation("malum", "spectral_lens"))
		tag(SOURCE_GEM)
			.addOptional(ResourceLocation("ars_nouveau", "source_gem"))

		// Curios tag
		val shields = ItemRegistry.REGISTRY.entries
			.map { it.get() }
			.filterIsInstance<SuperiorShieldItem<*>>()
			.toTypedArray()

		tag(CURIOS_TAG)
			.add(*shields)
	}
}