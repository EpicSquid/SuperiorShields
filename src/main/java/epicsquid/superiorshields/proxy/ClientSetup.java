package epicsquid.superiorshields.proxy;

import epicsquid.superiorshields.SuperiorShields;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = SuperiorShields.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

	@SubscribeEvent
	public static void setup(FMLClientSetupEvent event) {
		// Shield Rendering on Player
//		LayerShield shield = new LayerShield();
//		Minecraft.getInstance().getRenderManager().getSkinMap().get("default").addLayer(shield);
//		Minecraft.getInstance().getRenderManager().getSkinMap().get("slim").addLayer(shield);
	}
}
