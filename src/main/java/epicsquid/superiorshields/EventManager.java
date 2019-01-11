package epicsquid.superiorshields;

import javax.annotation.Nonnull;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import epicsquid.superiorshields.item.ISuperiorShield;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventManager {

  @SubscribeEvent
  public void onLivingHurtEvent(@Nonnull LivingHurtEvent event) {
    if (event.getEntity() instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) event.getEntity();
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
