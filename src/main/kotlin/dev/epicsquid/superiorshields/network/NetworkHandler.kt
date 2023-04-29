package dev.epicsquid.superiorshields.network

import dev.epicsquid.superiorshields.SuperiorShields
import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
import net.minecraft.client.Minecraft
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.network.NetworkRegistry
import net.minecraftforge.network.simple.SimpleChannel

object NetworkHandler {
	const val PROTOCOL_VERSION = "1.0"

	private var id = 0
	private fun nextId(): Int = id++

	val channel: SimpleChannel by lazy {
		NetworkRegistry.newSimpleChannel(
			ResourceLocation(SuperiorShields.MODID, "network"),
			{ PROTOCOL_VERSION },
			{ true },
			{ true })
	}

	// TODO wtf is going on here? Is it actually a false positive?
	@Suppress("INACCESSIBLE_TYPE")
	fun register() {
		channel.registerMessage(
			nextId(),
			SuperiorShieldUpdatePacket::class.java,
			{ msg, byteBuf -> encodeTo(byteBuf, msg) },
			::decodeFrom,
			{ msg, ctx ->
				ctx.get().apply {
					enqueueWork {
						Minecraft.getInstance().player?.let { player ->
							player.shield.hp = msg.hp
						}
					}
					packetHandled = true
				}
			}
		)
	}
}