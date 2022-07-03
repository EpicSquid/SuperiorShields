package epicsquid.superiorshields.network;

import epicsquid.superiorshields.capability.shield.CapabilityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketShieldUpdate {

	private final float currentHp;
	private final float maxHp;

	public PacketShieldUpdate(float currentHp, float maxHp) {
		this.currentHp = currentHp;
		this.maxHp = maxHp;
	}

	public static void encode(PacketShieldUpdate message, FriendlyByteBuf buf) {
		buf.writeFloat(message.currentHp);
		buf.writeFloat(message.maxHp);
	}

	public static PacketShieldUpdate decode(FriendlyByteBuf buf) {
		return new PacketShieldUpdate(buf.readFloat(), buf.readFloat());
	}

	public static void handle(PacketShieldUpdate msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			LocalPlayer player = Minecraft.getInstance().player;
			if (player != null) {
				CapabilityRegistry.getShield(player).ifPresent(shield -> {
					shield.setMaxHp(msg.maxHp);
					shield.setCurrentHp(msg.currentHp);
				});
			}
		});
		ctx.get().setPacketHandled(true);
	}
}
