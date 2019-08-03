package epicsquid.superiorshields.proxy;

import epicsquid.superiorshields.SuperiorShields;
import epicsquid.superiorshields.capability.shield.SuperiorShieldsCapabilityManager;
import epicsquid.superiorshields.network.NetworkHandler;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.Curios;
import top.theillusivec4.curios.api.CuriosAPI;
import top.theillusivec4.curios.api.imc.CurioIMCMessage;

public class ModSetup {

	public void setup(FMLCommonSetupEvent event) {
		SuperiorShieldsCapabilityManager.init();

		DeferredWorkQueue.runLater(NetworkHandler::register);
	}

	public void eneque(InterModEnqueueEvent event) {
		InterModComms.sendTo(Curios.MODID, CuriosAPI.IMC.REGISTER_TYPE,
				() -> new CurioIMCMessage(SuperiorShields.SHIELD_CURIO).setSize(1).setEnabled(true).setHidden(false));
	}
}
