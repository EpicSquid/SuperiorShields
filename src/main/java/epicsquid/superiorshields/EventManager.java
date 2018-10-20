package epicsquid.superiorshields;

import javax.annotation.Nonnull;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import epicsquid.superiorshields.init.ModItems;
import epicsquid.superiorshields.item.ISuperiorShield;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventManager {

  @SubscribeEvent
  public void onLivingHurtEvent(@Nonnull LivingHurtEvent event) {
    if (event.getEntity() instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) event.getEntity();
      int slot = BaublesApi.isBaubleEquipped(player, ModItems.test_shield);
      if (slot != -1) {
        IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
        ItemStack stack = handler.getStackInSlot(slot);
        if (stack.getItem() instanceof ISuperiorShield) {
          event.setAmount(((ISuperiorShield) stack.getItem()).applyShield(player, event.getAmount()));
        }
      }
    }
  }

}
