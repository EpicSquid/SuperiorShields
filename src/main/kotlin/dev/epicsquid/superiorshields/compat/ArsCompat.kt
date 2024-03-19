package dev.epicsquid.superiorshields.compat

import dev.epicsquid.superiorshields.effects.EffectTrigger
import dev.epicsquid.superiorshields.item.SuperiorShieldItem
import dev.epicsquid.superiorshields.shield.ArsManaSuperiorShield
import dev.epicsquid.superiorshields.shield.DurabilitySuperiorShield
import dev.epicsquid.superiorshields.shield.ReactiveHelper
import net.minecraft.world.item.Item.Properties
import net.minecraft.world.item.crafting.Ingredient
import net.minecraftforge.fml.ModList

object ArsCompat {

	private val loaded: Boolean by lazy { ModList.get().isLoaded("ars_nouveau") }

	fun arsManaShieldBuilder(
		props: Properties,
		enchantmentValue: Int,
		type: DurabilitySuperiorShield,
		repairItem: () -> Ingredient?,
	): SuperiorShieldItem<*> =
		if (loaded) {
			LoadedOnly.arsManaShieldBuilder(props, enchantmentValue, type, repairItem)
		} else {
			SuperiorShieldItem(
				props = props,
				enchantmentValue = enchantmentValue,
				type = type,
				repairItem = repairItem
			)
		}

	fun triggerReactive(effectTrigger: EffectTrigger) {
		if (loaded) LoadedOnly.triggerReactive(effectTrigger)
	}

	object LoadedOnly {
		fun arsManaShieldBuilder(
			props: Properties,
			enchantmentValue: Int,
			type: DurabilitySuperiorShield,
			repairItem: () -> Ingredient?,
		): SuperiorShieldItem<*> =
			SuperiorShieldItem(
				props = props,
				enchantmentValue = enchantmentValue,
				type = ArsManaSuperiorShield(type.config),
				repairItem = repairItem
			)


		fun triggerReactive(effectTrigger: EffectTrigger) {
			ReactiveHelper.triggerReactive(effectTrigger)
		}
	}
}