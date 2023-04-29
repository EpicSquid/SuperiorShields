package dev.epicsquid.superiorshields.item

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import dev.epicsquid.superiorshields.capability.SuperiorShieldCap
import dev.epicsquid.superiorshields.network.NetworkHandler
import dev.epicsquid.superiorshields.network.SuperiorShieldUpdatePacket
import dev.epicsquid.superiorshields.registry.AttributeRegistry
import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
import dev.epicsquid.superiorshields.shield.SuperiorShield
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraftforge.network.PacketDistributor
import top.theillusivec4.curios.api.SlotContext
import top.theillusivec4.curios.api.type.capability.ICurioItem
import java.util.*

class SuperiorShieldItem<T : SuperiorShield>(
	props: Properties,
	type: T
) : Item(props), ICurioItem {
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
		val delay: Int = slotContext.entity.getAttributeValue(AttributeRegistry.shieldDelay).toInt()
		val capacity: Int = slotContext.entity.getAttributeValue(AttributeRegistry.shieldCapacity).toInt()
		val rate: Int = slotContext.entity.getAttributeValue(AttributeRegistry.shieldRate).toInt()

		// Check if the shield is full
		if (shield.hp >= capacity) {
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
			if (shield.ticksWithoutDamage >= delay) {
				if (shield.ticksSinceRecharge % rate == 0) {
					// TODO recharge shield
					updateClient(slotContext.entity as Player, shield)
					// TODO trigger recharge effect
					if (shield.hp >= capacity) {
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
	}

	override fun canEquipFromUse(slotContext: SlotContext?, stack: ItemStack?): Boolean = true
}