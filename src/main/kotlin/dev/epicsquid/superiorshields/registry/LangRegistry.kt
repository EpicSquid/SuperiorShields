package dev.epicsquid.superiorshields.registry

import dev.epicsquid.superiorshields.SuperiorShields
import net.minecraft.resources.ResourceLocation
import top.theillusivec4.curios.api.CuriosApi

object LangRegistry {

	val REGISTRATE = SuperiorShields.registrate

	// Tooltips
	val BLANK = REGISTRATE.addLang("tooltip", ResourceLocation(SuperiorShields.MODID, "blank"), " ")
	val HP = REGISTRATE.addLang("tooltip", ResourceLocation(SuperiorShields.MODID, "hp"), " %s Hit Points")
	val RECHARGE_RATE =
		REGISTRATE.addLang("tooltip", ResourceLocation(SuperiorShields.MODID, "recharge_rate"), " %s Recharge Rate")
	val RECHARGE_DELAY =
		REGISTRATE.addLang("tooltip", ResourceLocation(SuperiorShields.MODID, "recharge_delay"), " %s Recharge Delay")
	val ENERGY = REGISTRATE.addLang("tooltip", ResourceLocation(SuperiorShields.MODID, "energy"), "Energy: %s / %sK FE")
	val EQUIP = REGISTRATE.addLang(
		"tooltip",
		ResourceLocation(SuperiorShields.MODID, "equip"),
		"When in the Superior Shield curios slot:"
	)

	// Curios specific langs
	val SHIELD_CURIOS = REGISTRATE.addRawLang(CuriosApi.MODID + ".identifier.superior_shield", "Superior Shield")
	val MODIFIERS =
		REGISTRATE.addRawLang(CuriosApi.MODID + ".modifiers.superior_shield", "When in the Superior Shield curios slot:")
	val CREATIVE_TAB = REGISTRATE.addRawLang("itemGroup." + SuperiorShields.MODID, "Superior Shields")
}