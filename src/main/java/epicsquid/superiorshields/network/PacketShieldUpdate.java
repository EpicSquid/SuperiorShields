package epicsquid.superiorshields.network;

import epicsquid.superiorshields.capability.shield.CapabilityRegistry;
import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketShieldUpdate {

	private float currentHp;
	private float maxHp;

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
				var shieldOp = CapabilityRegistry.getShield(player).resolve();
				if (shieldOp.isPresent()) {
					IShieldCapability shield = shieldOp.get();
					shield.setMaxHp(msg.maxHp);
					shield.setCurrentHp(msg.currentHp);
				}
			}
		});
		ctx.get().setPacketHandled(true);
	}
}
