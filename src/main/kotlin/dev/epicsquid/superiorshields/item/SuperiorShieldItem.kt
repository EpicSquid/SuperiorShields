package dev.epicsquid.superiorshields.item

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import dev.epicsquid.superiorshields.capability.SuperiorShieldCap
import dev.epicsquid.superiorshields.network.NetworkHandler
import dev.epicsquid.superiorshields.network.SuperiorShieldUpdatePacket
import dev.epicsquid.superiorshields.registry.AttributeRegistry
import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
import dev.epicsquid.superiorshields.registry.LangRegistry
import dev.epicsquid.superiorshields.shield.BotaniaSuperiorShield
import dev.epicsquid.superiorshields.shield.SuperiorShield
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.network.PacketDistributor
import top.theillusivec4.curios.api.SlotContext
import top.theillusivec4.curios.api.type.capability.ICurioItem
import java.text.DecimalFormat
import java.util.*

class SuperiorShieldItem<T : SuperiorShield>(
	props: Properties,
	private val type: T
) : Item(props), ICurioItem, SuperiorShield by type {
	companion object {
		val CAPACITY_UUID: UUID = UUID.fromString("e3c5b4a0-3f1a-4b1a-9b1a-5a4b1a3f1a3f")
		val RATE_UUID: UUID = UUID.fromString("e3c5b4a0-3f1a-4b1a-9b1a-5a4b1a3f1a3d")
		val DELAY_UUID: UUID = UUID.fromString("e3c5b4a0-3f1a-4b1a-9b1a-5a4b1a3f1a3e")
	}

	private val defaultModifiers: Multimap<Attribute, AttributeModifier>

	init {
		defaultModifiers = ImmutableMultimap.builder<Attribute, AttributeModifier>().apply {
			put(
				AttributeRegistry.shieldCapacity,
				AttributeModifier(CAPACITY_UUID, "Shield Capacity", type.capacity, AttributeModifier.Operation.ADDITION)
			)
			put(
				AttributeRegistry.shieldRate,
				AttributeModifier(RATE_UUID, "Shield Rate", type.rate.toDouble(), AttributeModifier.Operation.ADDITION)
			)
			put(
				AttributeRegistry.shieldDelay,
				AttributeModifier(DELAY_UUID, "Shield Delay", type.delay.toDouble(), AttributeModifier.Operation.ADDITION)
			)
		}.build()
	}

	override fun getAttributeModifiers(
		slotContext: SlotContext?,
		uuid: UUID?,
		stack: ItemStack
	): Multimap<Attribute, AttributeModifier> {
		// TODO enchantment checks go here to boost default stats
		return defaultModifiers
	}

	private fun updateClient(player: Player, shield: SuperiorShieldCap) =
		NetworkHandler.CHANNEL.send(
			PacketDistributor.PLAYER.with {
				player as ServerPlayer
			},
			SuperiorShieldUpdatePacket(shield.hp)
		)

	override fun curioTick(slotContext: SlotContext, stack: ItemStack) {
		if (slotContext.entity !is Player) return
		if (slotContext.entity.level.isClientSide) return

		val shield = slotContext.entity.shield

		// Get the shield stats
		val delayAttribute: Int = slotContext.entity.getAttributeValue(AttributeRegistry.shieldDelay).toInt()
		val capacityAttribute: Int = slotContext.entity.getAttributeValue(AttributeRegistry.shieldCapacity).toInt()
		val rateAttribute: Int = slotContext.entity.getAttributeValue(AttributeRegistry.shieldRate).toInt()

		// Check if the shield is full
		if (shield.hp >= capacityAttribute) {
			if (shield.ticksFull % 20 == 0) {
				// TODO trigger effect for being full
				// TODO determine if we need to update client?

				// Full trigger has occurred, reset
				shield.ticksFull = 0
			}
			// Increment how long the shield has been full for
			shield.ticksFull++
		} else {
			// It's not, so start recharging cycle
			if (shield.ticksWithoutDamage >= delayAttribute) {
				if (shield.ticksSinceRecharge % rateAttribute == 0) {
					type.rechargeShield(stack, slotContext.entity as Player)
					updateClient(slotContext.entity as Player, shield)
					// TODO trigger recharge effect
					if (shield.hp >= capacityAttribute) {
						// TODO trigger filled effect
					}
					// A recharge has occurred, reset
					shield.ticksSinceRecharge = 0
				}
				// Increment how long it has been since a recharge was attempted on the shield
				shield.ticksSinceRecharge++
			} else {
				// Increment how long it has been since the shield has been damaged
				shield.ticksWithoutDamage++
			}
		}

		// Repair the shield automatically if it is a Botania shield
		if (shield is BotaniaSuperiorShield) shield.repairWithMana(stack, slotContext.entity as Player)
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