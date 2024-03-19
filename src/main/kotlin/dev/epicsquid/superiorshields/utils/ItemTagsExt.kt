package dev.epicsquid.superiorshields.utils

import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

val String.forgeTag: TagKey<Item>
	get() = ItemTags.create(ResourceLocation("forge", this))
