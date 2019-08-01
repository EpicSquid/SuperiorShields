package epicsquid.superiorshields;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import epicsquid.superiorshields.item.ISuperiorShield;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = SuperiorShields.MODID)
public class EventManager {

  @SubscribeEvent
  public static void onLivingHurtEvent(@Nonnull LivingHurtEvent event) {
    if (event.getEntity() instanceof PlayerEntity) {
      PlayerEntity player = (PlayerEntity) event.getEntity();
      IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
      if (handler != null && event.getSource() != DamageSource.STARVE && event.getSource() != DamageSource.DROWN) {
        ItemStack stack = handler.getStackInSlot(BaubleType.CHARM.getValidSlots()[0]);
        if (!stack.isEmpty() && stack.getItem() instanceof ISuperiorShield) {
          event.setAmount(((ISuperiorShield) stack.getItem()).applyShield(player, stack, event.getAmount(), event.getSource()));
        }
      }
    }
  }

}
