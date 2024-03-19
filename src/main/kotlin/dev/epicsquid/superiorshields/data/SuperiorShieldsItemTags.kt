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
		val CURIOS_TAG = ItemTags.create(ResourceLocation(CuriosApi.MODID, SuperiorShieldsCurios.SUPERIOR_SHIELD_CURIO))
		val SUPERIOR_SHIELD_TAG = ItemTags.create(ResourceLocation(SuperiorShields.MODID, "superior_shield"))

		val MANAPEARL = "mana_pearl".forgeTag
		val PIXIE_DUST = "pixie_dust".forgeTag
		val FLUX_COIL = ItemTags.create(ResourceLocation("thermal", "flux_coil"))
		val COMPONENT_STEEL = "component_steel".forgeTag
		val WOODEN_GRIP = "wooden_grip".forgeTag
		val SOUL_STAINED_STEEL_INGOT = "soul_stained_steel_ingot".forgeTag
		val SPIRIT_FABRIC = ItemTags.create(ResourceLocation("malum", "spirit_fabric"))
		val HALLOWED_SPIRIT_RESONATOR = ItemTags.create(ResourceLocation("malum", "hallowed_spirit_resonator"))
		val STAINED_SPIRIT_RESONATOR = ItemTags.create(ResourceLocation("malum", "stained_spirit_resonator"))
		val SOURCE_GEM = "gem/source".forgeTag
		val MANASTEEL_INGOT = "ingots/manasteel".forgeTag
		val TERRASTEEL_INGOT = "ingots/terrasteel".forgeTag
		val ELEMENTIUM_INGOT = "ingots/elementium".forgeTag
		val STEEL_INGOT = "ingots/steel".forgeTag
		val ADVANCED_ALLOY = "alloys/advanced".forgeTag
		val BATTERIES = "batteries".forgeTag
		val TIN_GEAR = "gears/tin".forgeTag
	}

	override fun addTags(provider: Provider) {
		tag(MANAPEARL).addOptional(ResourceLocation("botania", "mana_pearl"))
		tag(PIXIE_DUST).addOptional(ResourceLocation("botania", "pixie_dust"))
		tag(FLUX_COIL).addOptional(ResourceLocation("thermal", "rf_coil"))
		tag(COMPONENT_STEEL).addOptional(ResourceLocation("immersiveengineering", "component_steel"))
		tag(WOODEN_GRIP).addOptional(ResourceLocation("immersiveengineering", "wooden_grip"))
		tag(SOUL_STAINED_STEEL_INGOT).addOptional(ResourceLocation("malum", "soul_stained_steel_ingot"))
		tag(SPIRIT_FABRIC).addOptional(ResourceLocation("malum", "spirit_fabric"))
		tag(HALLOWED_SPIRIT_RESONATOR).addOptional(ResourceLocation("malum", "hallowed_spirit_resonator"))
		tag(STAINED_SPIRIT_RESONATOR).addOptional(ResourceLocation("malum", "stained_spirit_resonator"))
		tag(SOURCE_GEM).addOptional(ResourceLocation("ars_nouveau", "source_gem"))

		// Curios tag
		val shields =
			ItemRegistry.REGISTRY.entries.map { it.get() }.filterIsInstance<SuperiorShieldItem<*>>().toTypedArray()
		tag(CURIOS_TAG)
			.add(*shields)
		tag(SUPERIOR_SHIELD_TAG)
			.add(*shields)
	}
}