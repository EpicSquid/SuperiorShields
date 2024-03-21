package dev.epicsquid.superiorshields.data

import com.google.common.base.Supplier
import com.google.common.base.Suppliers
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.epicsquid.superiorshields.registry.ItemRegistry
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraftforge.common.loot.IGlobalLootModifier
import net.minecraftforge.common.loot.LootModifier
import net.minecraftforge.registries.ForgeRegistries

class ShieldDungeonLootModifier(
	conditionsIn: Array<out LootItemCondition>,
	private val commonRolls: Int = 3,
	private val commonChance: Double = 0.3,
	private val commonShieldItems: List<Item> = listOf(
		ItemRegistry.ironShield,
		ItemRegistry.copperShield,
	),
	private val enchantmentLevel: Int = 5,
	private val rareRolls: Int = 1,
	private val rareChance: Double = 0.1,
	private val rareShieldItems: List<Item> = listOf(
		ItemRegistry.goldenShield,
		ItemRegistry.diamondShield
	),
) : LootModifier(conditionsIn) {
	companion object {
		val CODEC: Supplier<Codec<ShieldDungeonLootModifier>> =
			Suppliers.memoize {
				RecordCodecBuilder.create { inst ->
					codecStart(inst)
						.and(
							inst.group(
								Codec.INT.fieldOf("common_rolls").forGetter { it.commonRolls },
								Codec.DOUBLE.fieldOf("common_chance").forGetter { it.commonChance },
								ForgeRegistries.ITEMS.codec.listOf().fieldOf("common_shield_items")
									.forGetter { it.commonShieldItems.toList() },
								Codec.INT.fieldOf("enchantment_level").forGetter { it.enchantmentLevel },
								Codec.INT.fieldOf("rare_rolls").forGetter { it.rareRolls },
								Codec.DOUBLE.fieldOf("rare_chance").forGetter { it.rareChance },
								ForgeRegistries.ITEMS.codec.listOf().fieldOf("rare_shield_items")
									.forGetter { it.rareShieldItems.toList() },
							)
						).apply(inst, ::ShieldDungeonLootModifier)
				}
			}
	}

	override fun codec(): Codec<out IGlobalLootModifier> = CODEC.get()


	override fun doApply(generatedLoot: ObjectArrayList<ItemStack>, context: LootContext): ObjectArrayList<ItemStack> {
		for (i in 0 until commonRolls) {
			if (context.random.nextDouble() < commonChance) {
				val item = commonShieldItems[context.random.nextInt(commonShieldItems.size)]
				val stack = ItemStack(item)
				generatedLoot.add(EnchantmentHelper.enchantItem(context.random, stack, enchantmentLevel, true))
			}
		}

		for (i in 0 until rareRolls) {
			if (context.random.nextDouble() < rareChance) {
				val item = rareShieldItems[context.random.nextInt(rareShieldItems.size)]
				val stack = ItemStack(item)
				generatedLoot.add(EnchantmentHelper.enchantItem(context.random, stack, enchantmentLevel * 3, true))
			}
		}
		return generatedLoot
	}
}