package epicsquid.superiorshields.proxy;

import epicsquid.superiorshields.SuperiorShields;
import epicsquid.superiorshields.capability.shield.SuperiorShieldsCapabilityManager;
import epicsquid.superiorshields.item.SuperiorShield;
import epicsquid.superiorshields.network.NetworkHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;

public class ModSetup {

	public void setup(FMLCommonSetupEvent event) {
		SuperiorShieldsCapabilityManager.init();
		NetworkHandler.register();
	}

	public void enqueue(InterModEnqueueEvent event) {
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE,
						() -> new SlotTypeMessage.Builder(SuperiorShields.SHIELD_CURIO)
										.build());
	}
}
