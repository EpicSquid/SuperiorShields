package epicsquid.superiorshields;

import java.util.HashMap;

import javax.annotation.Nonnull;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import epicsquid.superiorshields.capability.IShieldCapability;
import epicsquid.superiorshields.capability.SuperiorShieldsCapabilityManager;
import epicsquid.superiorshields.item.ISuperiorShield;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventManager {

  private HashMap<EntityPlayer, Integer> HURT_TIME = new HashMap<>();

  @SubscribeEvent
  public void onLivingHurtEvent(@Nonnull LivingHurtEvent event) {
    if (event.getEntity() instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) event.getEntity();
      IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
      if (handler != null && event.getSource() != DamageSource.STARVE && event.getSource() != DamageSource.DROWN) {
        ItemStack stack = handler.getStackInSlot(BaubleType.BELT.getValidSlots()[0]);
        if (!stack.isEmpty() && stack.getItem() instanceof ISuperiorShield) {
          event.setAmount(((ISuperiorShield) stack.getItem()).applyShield(player, stack, event.getAmount()));
        }
      }
    }
  }

  @SubscribeEvent
  public void onTick(TickEvent.PlayerTickEvent event) {
    if (event.phase == TickEvent.Phase.START && HURT_TIME.containsKey(event.player)) {
      HURT_TIME.put(event.player, HURT_TIME.get(event.player) - 1);
      if (HURT_TIME.get(event.player) < 0)
        HURT_TIME.remove(event.player);
    }
  }

  @SubscribeEvent
  public void onRenderPlayer(RenderPlayerEvent.Pre event) {
    EntityPlayer player = event.getEntityPlayer();
    if (player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null)) {
      IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
      if (shield.getCurrentHp() > 0 && player.getLastDamageSource() != DamageSource.STARVE && player.getLastDamageSource() != DamageSource.DROWN) {
        if (event.getEntityPlayer().hurtTime > 0) {
          HURT_TIME.put(event.getEntityPlayer(), event.getEntityPlayer().hurtTime);
          event.getEntityPlayer().hurtTime = 0;
        }
        if (HURT_TIME.containsKey(event.getEntityPlayer())) {
          GlStateManager.color(204 / 256f, 255 / 255f, 251 / 255f, 1);
        }
      }
    }

  }

}
