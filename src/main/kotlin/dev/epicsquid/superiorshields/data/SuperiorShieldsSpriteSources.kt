package dev.epicsquid.superiorshields.data

import dev.epicsquid.superiorshields.SuperiorShields
import net.minecraft.client.renderer.texture.atlas.sources.SingleFile
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.inventory.InventoryMenu
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.common.data.SpriteSourceProvider
import java.util.*

class SuperiorShieldsSpriteSources(
	output: PackOutput,
	fileHelper: ExistingFileHelper
) : SpriteSourceProvider(output, fileHelper, SuperiorShields.MODID) {

	override fun addSources() {
		atlas(InventoryMenu.BLOCK_ATLAS).addSource(
			SingleFile(
				ResourceLocation(
					SuperiorShields.MODID,
					"item/empty_shield_slot"
				), Optional.empty()
			)
		)
	}
}