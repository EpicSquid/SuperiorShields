package epicsquid.superiorshields.setup;

import epicsquid.superiorshields.SuperiorShields;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.InterModComms;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;

public class ModSetup {

	public static void sendImc() {
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE,
						() -> (new SlotTypeMessage.Builder(SuperiorShields.SHIELD_CURIO).priority(220).icon(new ResourceLocation(SuperiorShields.MODID, "item/empty_shield_slot"))).build());
	}
}
