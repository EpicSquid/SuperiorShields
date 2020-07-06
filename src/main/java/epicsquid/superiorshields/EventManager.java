package epicsquid.superiorshields;

import epicsquid.superiorshields.item.SuperiorShield;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = SuperiorShields.MODID)
public class EventManager {

	@SubscribeEvent
	public static void onLivingHurtEvent(@Nonnull LivingHurtEvent event) {
		if (event.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntity();
			if (CuriosApi.getCuriosHelper().getCuriosHandler(player).isPresent() && event.getSource() != DamageSource.STARVE && event.getSource() != DamageSource.DROWN) {
				ICuriosItemHandler handler = CuriosApi.getCuriosHelper().getCuriosHandler(player).orElse(null);
				handler.getStacksHandler(SuperiorShields.SHIELD_CURIO).ifPresent(
					stackHandler -> {
						ItemStack stack = stackHandler.getStacks().getStackInSlot(0);
						if (!stack.isEmpty() && stack.getItem() instanceof SuperiorShield) {
							event.setAmount(((SuperiorShield) stack.getItem()).applyShield(player, stack, event.getAmount(), event.getSource()));
						}
					}
				);
			}
		}
	}

}
