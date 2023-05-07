package dev.epicsquid.superiorshields.item

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import dev.epicsquid.superiorshields.enchantment.AttributeProvider
import dev.epicsquid.superiorshields.registry.AttributeRegistry
import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
import dev.epicsquid.superiorshields.registry.LangRegistry
import dev.epicsquid.superiorshields.shield.SuperiorShield
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.level.Level
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import top.theillusivec4.curios.api.SlotContext
import top.theillusivec4.curios.api.type.capability.ICurioItem
import java.text.DecimalFormat
import java.util.*

class SuperiorShieldItem<T : SuperiorShield>(
	props: Properties,
	private val enchantmentValue: Int,
	private val type: T
) : Item(props), ICurioItem, SuperiorShield by type {
	companion object {
		val CAPACITY_UUID: UUID = UUID.fromString("0ac7a83c-9c61-408b-bf21-4050a95321b3")
		val RATE_UUID: UUID = UUID.fromString("ab9a96c0-955c-4198-ae9e-fa3c536f7032")
		val DELAY_UUID: UUID = UUID.fromString("ca728566-0404-4ae1-a4ab-1a1ff4074a72")
	}

	private val defaultModifiers: Multimap<Attribute, AttributeModifier>

	init {
		defaultModifiers = ImmutableMultimap.builder<Attribute, AttributeModifier>().apply {
			put(
				AttributeRegistry.shieldCapacity,
				AttributeModifier(CAPACITY_UUID, "Shield Capacity", type.capacity.toDouble(), AttributeModifier.Operation.ADDITION)
			)
			put(
				AttributeRegistry.shieldRate,
				AttributeModifier(RATE_UUID, "Shield Recharge Rate", type.rate.toDouble(), AttributeModifier.Operation.ADDITION)
			)
			put(
				AttributeRegistry.shieldDelay,
				AttributeModifier(DELAY_UUID, "Shield Recharge Delay", type.delay.toDouble(), AttributeModifier.Operation.ADDITION)
			)
		}.build()
	}

	override fun getAttributeModifiers(
		slotContext: SlotContext?,
		uuid: UUID?,
		stack: ItemStack
	): Multimap<Attribute, AttributeModifier> {
		return ImmutableMultimap.builder<Attribute, AttributeModifier>().apply {
			putAll(defaultModifiers)
			EnchantmentHelper.getEnchantments(stack).filter { it.key is AttributeProvider }
				.forEach { (enchantment, level) ->
					val provider = enchantment as AttributeProvider
					putAll(provider.getAttributeModifiers(slotContext, uuid, level))
				}
		}.build()
	}

	override fun getEnchantmentValue(stack: ItemStack?): Int {
		return enchantmentValue
	}

	override fun curioTick(slotContext: SlotContext, stack: ItemStack) {
		shieldTick(slotContext.entity, slotContext.entity.shield, stack)
	}

	override fun canEquipFromUse(slotContext: SlotContext?, stack: ItemStack?): Boolean = true

	@OnlyIn(Dist.CLIENT)
	override fun appendHoverText(
		stack: ItemStack,
		level: Level?,
		tooltip: MutableList<Component>,
		isAdvanced: TooltipFlag
	) {
		super.appendHoverText(stack, level, tooltip, isAdvanced)

		val decimalFormat = DecimalFormat()
		decimalFormat.maximumFractionDigits = 2

		tooltip.apply {
			add(LangRegistry.BLANK)
			add(LangRegistry.EQUIP.withStyle(ChatFormatting.GRAY))
		}
	}
}