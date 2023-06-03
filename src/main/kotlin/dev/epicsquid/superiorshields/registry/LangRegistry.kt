package dev.epicsquid.superiorshields.registry

import dev.epicsquid.superiorshields.SuperiorShields
import net.minecraft.network.chat.MutableComponent
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

	// Enchantment tooltips
	val FIRE_NOVA =
		registerEnchantmentDescription("fire_nova", "On depletion, lights all mobs around you on fire.")
	val FROST_NOVA =
		registerEnchantmentDescription("frost_nova", "On depletion, slows and weakens all mobs around you.")
	val SHULKING_NOVA =
		registerEnchantmentDescription("shulking_nova", "On depletion, makes all mobs around you float.")
	val POISON_SPIKES = registerEnchantmentDescription(
		"poison_spikes",
		"On damage to shield, magical spikes will poison the attacker."
	)
	val WITHER_SPIKES = registerEnchantmentDescription(
		"wither_spikes",
		"On damage to shield, magical spikes will wither the attacker."
	)
	val CURING =
		registerEnchantmentDescription("curing", "On depletion, cures you of all potion effects, just like milk.")
	val CAPACITY = registerEnchantmentDescription("capacity", "Increases shield capacity.")
	val QUICKENED = registerEnchantmentDescription("quickened", "Increases shield recharge rate.")
	val RAGING = registerEnchantmentDescription("raging", "Increases your damage while your shield is depleted.")
	val AMPLIFY = registerEnchantmentDescription(
		"amplify",
		"While your shield is full, your next attack will deal significantly greater damage, at the cost of some shield energy."
	)

	fun registerEnchantmentDescription(enchantment: String, description: String): MutableComponent =
		REGISTRATE.addRawLang("enchantment.${SuperiorShields.MODID}.$enchantment.desc", description)

	fun classload() {}
}