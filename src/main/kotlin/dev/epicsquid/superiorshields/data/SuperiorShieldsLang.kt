package dev.epicsquid.superiorshields.data

import dev.epicsquid.superiorshields.SuperiorShields
import dev.epicsquid.superiorshields.registry.EnchantmentRegistry
import dev.epicsquid.superiorshields.registry.ItemRegistry
import net.minecraft.data.PackOutput
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraftforge.common.data.LanguageProvider
import net.minecraftforge.registries.ForgeRegistries

class SuperiorShieldsLang(
	output: PackOutput,
	locale: String
) : LanguageProvider(output, SuperiorShields.MODID, locale) {

	companion object {
		const val BLANK = "tooltip.${SuperiorShields.MODID}.blank"
		const val HP = "tooltip.${SuperiorShields.MODID}.hp"
		const val RECHARGE_RATE = "tooltip.${SuperiorShields.MODID}.recharge_rate"
		const val RECHARGE_DELAY = "tooltip.${SuperiorShields.MODID}.recharge_delay"
		const val ENERGY = "tooltip.${SuperiorShields.MODID}.energy"
		const val EQUIP = "tooltip.${SuperiorShields.MODID}.equip"
	}

	override fun addTranslations() {
		// Enchantments
		add(EnchantmentRegistry.curing, "Curing")
		add(EnchantmentRegistry.quickened, "Quickened")
		add(EnchantmentRegistry.capacity, "Shield Capacity")
		add(EnchantmentRegistry.raging, "Raging")
		add(EnchantmentRegistry.amplify, "Amplify")
		add(EnchantmentRegistry.fireNova, "Fire Nova")
		add(EnchantmentRegistry.frostNova, "Frost Nova")
		add(EnchantmentRegistry.shulkingNova, "Shulking Nova")
		add(EnchantmentRegistry.poisonSpikes, "Poison Spikes")
		add(EnchantmentRegistry.witherSpikes, "Wither Spikes")
		add(EnchantmentRegistry.flameOfTheFirehawk, "Flame of the Firehawk")
		add(EnchantmentRegistry.theBee, "The Bees!")

		addEnchantmentDesc(EnchantmentRegistry.curing, "On depletion, cures you of all potion effects, just like milk.")
		addEnchantmentDesc(EnchantmentRegistry.quickened, "Increases shield recharge rate.")
		addEnchantmentDesc(EnchantmentRegistry.capacity, "Increases shield capacity.")
		addEnchantmentDesc(EnchantmentRegistry.raging, "Increases your damage while your shield is depleted.")
		addEnchantmentDesc(
			EnchantmentRegistry.amplify,
			"While your shield is full, your next attack will deal significantly greater damage, at the cost of some shield energy."
		)
		addEnchantmentDesc(EnchantmentRegistry.fireNova, "On depletion, lights all mobs around you on fire.")
		addEnchantmentDesc(EnchantmentRegistry.frostNova, "On depletion, slows and weakens all mobs around you.")
		addEnchantmentDesc(EnchantmentRegistry.shulkingNova, "On depletion, makes all mobs around you float.")
		addEnchantmentDesc(
			EnchantmentRegistry.poisonSpikes,
			"On damage to shield, magical spikes will poison the attacker."
		)
		addEnchantmentDesc(
			EnchantmentRegistry.witherSpikes,
			"On damage to shield, magical spikes will wither the attacker."
		)
		addEnchantmentDesc(
			EnchantmentRegistry.flameOfTheFirehawk,
			"On depletion, lights all mobs around you on fire, and continues to do so for a short time."
		)
		addEnchantmentDesc(
			EnchantmentRegistry.theBee,
			"While your shield is full, your attacks will deal significantly greater damage."
		)

		// Items
		add(ItemRegistry.ironShield, "Iron Shield")
		add(ItemRegistry.goldenShield, "Golden Shield")
		add(ItemRegistry.diamondShield, "Diamond Shield")
		add(ItemRegistry.netheriteShield, "Netherite Shield")
		add(ItemRegistry.enchanterShield, "Enchanter's Shield")
		add(ItemRegistry.tinShield, "Tin Shield")
		add(ItemRegistry.copperShield, "Copper Shield")
		add(ItemRegistry.silverShield, "Silver Shield")
		add(ItemRegistry.leadShield, "Lead Shield")
		add(ItemRegistry.bronzeShield, "Bronze Shield")
		add(ItemRegistry.nickelShield, "Nickel Shield")
		add(ItemRegistry.steelShield, "Steel Shield")
		add(ItemRegistry.electrumShield, "Electrum Shield")
		add(ItemRegistry.invarShield, "Invar Shield")
		add(ItemRegistry.constantanShield, "Constantan Shield")
		add(ItemRegistry.lapisShield, "Lapis Shield")
		add(ItemRegistry.osmiumShield, "Osmium Shield")
		add(ItemRegistry.refinedObsidianShield, "Refined Obsidian Shield")
		add(ItemRegistry.refinedGlowstoneShield, "Refined Glowstone Shield")
		add(ItemRegistry.steeleafShield, "Steeleaf Shield")
		add(ItemRegistry.fieryShield, "Fiery Shield")
		add(ItemRegistry.knightmetalShield, "Knightmetal Shield")
		add(ItemRegistry.ironwoodShield, "Ironwood Shield")
		add(ItemRegistry.electricShield, "Electric Shield")
		add(ItemRegistry.engineersShield, "Engineer's Shield")
		add(ItemRegistry.fluxShield, "Flux Shield")
		add(ItemRegistry.manasteelShield, "Manasteel Shield")
		add(ItemRegistry.terrasteelShield, "Terrasteel Shield")
		add(ItemRegistry.elementiumShield, "Elementium Shield")
		add(ItemRegistry.soulStainedShield, "Soul Stained Shield")
		add(ItemRegistry.spiritHunterShield, "Spirit Hunter Shield")

		// Other
		add("tooltip.${SuperiorShields.MODID}.blank", " ")
		add("tooltip.${SuperiorShields.MODID}.hp", " %s Hit Points")
		add("tooltip.${SuperiorShields.MODID}.recharge_rate", " %s Recharge Rate")
		add("tooltip.${SuperiorShields.MODID}.recharge_delay", " %s Recharge Delay")
		add("tooltip.${SuperiorShields.MODID}.energy", "Energy: %s / %sK FE")
		add("tooltip.${SuperiorShields.MODID}.equip", "When in the Superior Shield curios slot:")
		add("itemGroup.${SuperiorShields.MODID}", "Superior Shields")
		add("curios.identifier.${SuperiorShieldsCurios.SUPERIOR_SHIELD_CURIO}", "Superior Shield")
		add("curios.modifiers.${SuperiorShieldsCurios.SUPERIOR_SHIELD_CURIO}", "When in the Superior Shield curios slot:")
	}
}

fun LanguageProvider.addEnchantmentDesc(enchantment: Enchantment, desc: String) {
	val rl = ForgeRegistries.ENCHANTMENTS.getKey(enchantment)!!
	add("enchantment.${rl.namespace}.${rl.path}.desc", desc)
}