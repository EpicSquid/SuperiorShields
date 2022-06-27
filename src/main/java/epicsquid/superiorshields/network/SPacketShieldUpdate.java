package epicsquid.superiorshields.network;

import java.util.function.Supplier;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.capability.shield.CapabilityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class SPacketShieldUpdate {

	private float currentHp;
	private float maxHp;

	public SPacketShieldUpdate(float currentHp, float maxHp) {
		this.currentHp = currentHp;
		this.maxHp = maxHp;
	}

	public static void encode(SPacketShieldUpdate message, PacketBuffer buf) {
		buf.writeFloat(message.currentHp);
		buf.writeFloat(message.maxHp);
	}

	public static SPacketShieldUpdate decode(PacketBuffer buf) {
		return new SPacketShieldUpdate(buf.readFloat(), buf.readFloat());
	}

	public static void handle(SPacketShieldUpdate msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ClientPlayerEntity player = Minecraft.getInstance().player;
			if (player.getCapability(CapabilityRegistry.shieldCapability).isPresent()) {
				IShieldCapability shield = player.getCapability(CapabilityRegistry.shieldCapability).orElseGet(() -> null);
				shield.setMaxHp(msg.maxHp);
				shield.setCurrentHp(msg.currentHp);
			}
		});
		ctx.get().setPacketHandled(true);
	}
}
