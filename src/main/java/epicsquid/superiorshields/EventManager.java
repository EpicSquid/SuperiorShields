package epicsquid.superiorshields;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.item.ISuperiorShield;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosAPI;
import top.theillusivec4.curios.api.capability.ICurioItemHandler;

@Mod.EventBusSubscriber(modid = SuperiorShields.MODID)
public class EventManager {

	@SubscribeEvent
	public static void onLivingHurtEvent(@Nonnull LivingHurtEvent event) {
		if (event.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntity();
			if (CuriosAPI.getCuriosHandler(player).isPresent() && event.getSource() != DamageSource.STARVE && event.getSource() != DamageSource.DROWN) {
				ICurioItemHandler handler = CuriosAPI.getCuriosHandler(player).orElse(null);
				ItemStack stack = handler.getStackInSlot(SuperiorShields.SHIELD_CURIO, 0);
				if (!stack.isEmpty() && stack.getItem() instanceof ISuperiorShield) {
					event.setAmount(((ISuperiorShield) stack.getItem()).applyShield(player, stack, event.getAmount(), event.getSource()));
				}
			}
		}
	}

}
