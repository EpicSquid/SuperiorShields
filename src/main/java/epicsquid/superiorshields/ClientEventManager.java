package epicsquid.superiorshields;

import java.util.HashMap;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.capability.shield.SuperiorShieldsCapabilityManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = SuperiorShields.MODID, value=Side.CLIENT)
public class ClientEventManager {

  private static HashMap<EntityPlayer, Integer> HURT_TIME = new HashMap<>();

  @SubscribeEvent
  public static void onRenderPlayer(RenderPlayerEvent.Pre event) {
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
  
  @SubscribeEvent
  public static void onTick(TickEvent.PlayerTickEvent event) {
    if (event.phase == TickEvent.Phase.START && HURT_TIME.containsKey(event.player)) {
      HURT_TIME.put(event.player, HURT_TIME.get(event.player) - 1);
      if (HURT_TIME.get(event.player) < 0)
        HURT_TIME.remove(event.player);
    }
  }

	
}
