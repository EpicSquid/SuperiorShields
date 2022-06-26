package epicsquid.superiorshields.network;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import epicsquid.superiorshields.SuperiorShields;
import net.minecraft.network.PacketBuffer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
			.named(new ResourceLocation(SuperiorShields.MODID, "main"))
			.clientAcceptedVersions(PROTOCOL_VERSION::equals)
			.serverAcceptedVersions(PROTOCOL_VERSION::equals)
			.networkProtocolVersion(() -> PROTOCOL_VERSION)
			.simpleChannel();

	private static int id = 0;

	public static void register() {
		registerMessage(SPacketShieldUpdate.class, SPacketShieldUpdate::encode, SPacketShieldUpdate::decode, SPacketShieldUpdate::handle);
	}

	private static <M> void registerMessage(Class<M> messageType, BiConsumer<M, PacketBuffer> encoder, Function<PacketBuffer, M> decoder, BiConsumer<M, Supplier<NetworkEvent.Context>> messageConsumer) {
		INSTANCE.registerMessage(id++, messageType, encoder, decoder, messageConsumer);
	}
}
