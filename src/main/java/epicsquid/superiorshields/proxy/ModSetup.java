package epicsquid.superiorshields.proxy;

import epicsquid.superiorshields.capability.shield.SuperiorShieldsCapabilityManager;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup {

	public void setup(FMLCommonSetupEvent event) {
		SuperiorShieldsCapabilityManager.preInit();
	}
}
