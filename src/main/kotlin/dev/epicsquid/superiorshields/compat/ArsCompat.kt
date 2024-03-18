package dev.epicsquid.superiorshields.compat

import com.tterrag.registrate.util.nullness.NonNullFunction
import dev.epicsquid.superiorshields.effects.EffectTrigger
import dev.epicsquid.superiorshields.item.SuperiorShieldItem
import dev.epicsquid.superiorshields.shield.ArsManaSuperiorShield
import dev.epicsquid.superiorshields.shield.DurabilitySuperiorShield
import dev.epicsquid.superiorshields.shield.ReactiveHelper
import net.minecraft.world.item.Item
import net.minecraft.world.item.Item.Properties
import net.minecraft.world.item.crafting.Ingredient
import net.minecraftforge.common.util.NonNullFunction
import net.minecraftforge.fml.ModList

object ArsCompat {

	private val loaded: Boolean by lazy { ModList.get().isLoaded("ars_nouveau") }

	fun arsManaShieldBuilder(
		enchantmentValue: Int,
		type: DurabilitySuperiorShield,
		repairItem: () -> Ingredient?,
	): NonNullFunction<Item.Properties, SuperiorShieldItem<*>> =
		if (loaded) {
			LoadedOnly.arsManaShieldBuilder(enchantmentValue, type, repairItem)
		} else {
			NonNullFunction { props: Item.Properties ->
				SuperiorShieldItem(
					props = props,
					enchantmentValue = enchantmentValue,
					type = type,
					repairItem = repairItem
				)
			}
		}

	fun triggerReactive(effectTrigger: EffectTrigger) {
		if (loaded) LoadedOnly.triggerReactive(effectTrigger)
	}

	object LoadedOnly {
		fun arsManaShieldBuilder(
			enchantmentValue: Int,
			type: DurabilitySuperiorShield,
			repairItem: () -> Ingredient?,
		): NonNullFunction<Properties, SuperiorShieldItem<*>> =
			NonNullFunction { props: Item.Properties ->
				SuperiorShieldItem(
					props = props,
					enchantmentValue = enchantmentValue,
					type = ArsManaSuperiorShield(
						type.name,
						type.config
					),
					repairItem = repairItem
				)
			}

		fun triggerReactive(effectTrigger: EffectTrigger) {
			ReactiveHelper.triggerReactive(effectTrigger)
		}
	}
}