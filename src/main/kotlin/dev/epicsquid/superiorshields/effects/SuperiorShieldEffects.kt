package dev.epicsquid.superiorshields.effects

import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items

object SuperiorShieldEffects {

	val CURING_EFFECT: (LivingEntity) -> Unit = {
		it.curePotionEffects(ItemStack(Items.MILK_BUCKET))
	}
}