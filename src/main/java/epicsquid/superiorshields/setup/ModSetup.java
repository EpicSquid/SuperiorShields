package epicsquid.superiorshields.setup;

import epicsquid.superiorshields.SuperiorShields;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;

public class ModSetup {

	public static void sendImc() {
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder(SuperiorShields.SHIELD_CURIO).build());
	}
}
