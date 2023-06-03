package dev.epicsquid.superiorshields.data

import dev.epicsquid.superiorshields.SuperiorShields
import net.minecraft.data.DataGenerator
import net.minecraft.data.tags.BlockTagsProvider
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraftforge.common.data.ExistingFileHelper
import top.theillusivec4.curios.api.CuriosApi

class SuperiorShieldsTags(
	private val dataGenerator: DataGenerator,
	private val blockTagsProvider: BlockTagsProvider,
	private val existingFileHelper: ExistingFileHelper
) : ItemTagsProvider(dataGenerator, blockTagsProvider, SuperiorShields.MODID, existingFileHelper) {
	companion object {
		val MANAPEARL = ItemTags.create(ResourceLocation("forge", "mana_pearl"))
		val PIXIE_DUST = ItemTags.create(ResourceLocation("forge", "pixie_dust"))
		val FLUX_COIL = ItemTags.create(ResourceLocation("thermal", "flux_coil"))
		val COMPONENT_STEEL = ItemTags.create(ResourceLocation("forge", "component_steel"))
		val WOODEN_GRIP = ItemTags.create(ResourceLocation("forge", "wooden_grip"))
		val SOUL_STAINED_STEEL_INGOT = ItemTags.create(ResourceLocation("forge", "ingots/soul_stained_steel"))
		val SPIRIT_FABRIC = ItemTags.create(ResourceLocation("malum", "spirit_fabric"))
		val HALLOWED_SPIRIT_RESONATOR = ItemTags.create(ResourceLocation("malum", "hallowed_spirit_resonator"))
		val STAINED_SPIRIT_RESONATOR = ItemTags.create(ResourceLocation("malum", "stained_spirit_resonator"))
		val SOURCE_GEM = ItemTags.create(ResourceLocation("forge", "gem/source"))

		// These tags already exist, we just put them here so we cna use them easier
		val CURIOS_TAG = ItemTags.create(ResourceLocation(CuriosApi.MODID, SuperiorShields.SUPERIOR_SHIELD_CURIO))
		val SHIELD_TAG = ItemTags.create(ResourceLocation(SuperiorShields.MODID, "shield"))
		val MANASTEEL_INGOT = ItemTags.create(ResourceLocation("forge", "ingots/manasteel"))
		val TERRASTEEL_INGOT = ItemTags.create(ResourceLocation("forge", "ingots/terrasteel"))
		val ELEMENTIUM_INGOT = ItemTags.create(ResourceLocation("forge", "ingots/elementium"))
		val STEEL_INGOT = ItemTags.create(ResourceLocation("forge", "ingots/steel"))
		val ADVANCED_ALLOY = ItemTags.create(ResourceLocation("forge", "alloys/advanced"))
		val TIN_GEAR = ItemTags.create(ResourceLocation("forge", "gears/tin"))

	}

	override fun addTags() {
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
	}
}