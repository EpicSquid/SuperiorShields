package dev.epicsquid.superiorshields.data

import dev.epicsquid.superiorshields.SuperiorShields
import dev.epicsquid.superiorshields.registry.ItemRegistry
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition.Builder
import net.minecraftforge.common.data.GlobalLootModifierProvider
import net.minecraftforge.common.loot.LootTableIdCondition


class SuperiorShieldsGlobalLoot(output: PackOutput) : GlobalLootModifierProvider(output, SuperiorShields.MODID) {

	override fun start() {
		add(
			"basic_loot", ShieldDungeonLootModifier(
				conditionsIn = arrayOf(
					getList(
						arrayOf(
							"chests/simple_dungeon",
							"chests/jungle_temple",
							"chests/abandoned_mineshaft",
							"chests/desert_pyramid",
							"chests/pillager_outpost",
							"chests/stronghold_corridor",
							"chests/stronghold_crossing",
							"chests/stronghold_library",
							"chests/woodland_mansion",
							"chests/underwater_ruin_big",
							"chests/underwater_ruin_small",
							"chests/ruined_portal",
						)
					)
				),
				rareShieldItems = listOf(
					ItemRegistry.ironShield,
					ItemRegistry.goldenShield
				),
			)
		)
		add(
			"nether_loot", ShieldDungeonLootModifier(
				conditionsIn = arrayOf(
					getList(
						arrayOf(
							"chests/bastion_treasure",
							"chests/bastion_other",
							"chests/bastion_bridge",
							"chests/bastion_hoglin_stable",
							"chests/nether_bridge",
						)
					)
				),
				commonShieldItems = listOf(
					ItemRegistry.ironShield
				),
				enchantmentLevel = 10,
				rareRolls = 2,
				rareShieldItems = listOf(
					ItemRegistry.goldenShield,
					ItemRegistry.ironShield
				)
			)
		)
		add(
			"end_loot", ShieldDungeonLootModifier(
				conditionsIn = arrayOf(
					getList(
						arrayOf(
							"chests/end_city_treasure",
							"chests/ancient_city",
							"chests/ancient_city_ice_box",
						)
					)
				),
				commonRolls = 1,
				commonShieldItems = listOf(
					ItemRegistry.ironShield
				),
				rareShieldItems = listOf(
					ItemRegistry.diamondShield
				),
				enchantmentLevel = 15,
				rareRolls = 3
			)
		)
	}

	private fun getList(chests: Array<String>): LootItemCondition {
		var condition: Builder? = null

		for (s in chests) {
			if (condition == null) {
				condition = LootTableIdCondition.builder(ResourceLocation(s))
				continue
			}
			condition = condition.or(LootTableIdCondition.builder(ResourceLocation(s)))
		}
		return condition!!.build()
	}
}