package dev.epicsquid.superiorshields.data

import dev.epicsquid.superiorshields.SuperiorShields
import dev.epicsquid.superiorshields.registry.ItemRegistry
import net.minecraft.data.PackOutput
import net.minecraft.world.level.ItemLike
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.registries.ForgeRegistries

class SuperiorShieldsItemModels(
	output: PackOutput,
	existingFileHelper: ExistingFileHelper
) : ItemModelProvider(output, SuperiorShields.MODID, existingFileHelper) {

	override fun registerModels() {
		ItemRegistry.REGISTRY.entries.mapNotNull { it.get() }.forEach {
			generated(name(it))
		}
	}
}

fun ItemModelProvider.generated(name: String) {
	getBuilder(name)
		.parent(UncheckedModelFile("item/generated"))
		.texture("layer0", modLoc("item/$name"))
}

fun ItemModelProvider.name(item: ItemLike): String {
	return ForgeRegistries.ITEMS.getKey(item.asItem())!!.path
}