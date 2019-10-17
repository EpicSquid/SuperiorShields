package epicsquid.superiorshields;

import com.mojang.blaze3d.platform.GlStateManager;
import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.capability.shield.SuperiorShieldsCapabilityManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;

@Mod.EventBusSubscriber(modid = SuperiorShields.MODID, value = Dist.CLIENT)
public class ClientEventManager {

	private static HashMap<PlayerEntity, Integer> HURT_TIME = new HashMap<>();

	@SubscribeEvent
	public static void onRenderPlayer(RenderPlayerEvent.Pre event) {
		PlayerEntity player = event.getEntityPlayer();
		if (player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).isPresent()) {
			IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).orElseGet(() -> null);
			if (shield.getCurrentHp() > 0 && player.getLastDamageSource() != DamageSource.STARVE && player.getLastDamageSource() != DamageSource.DROWN) {
				if (event.getEntityPlayer().hurtTime > 0) {
					HURT_TIME.put(event.getEntityPlayer(), event.getEntityPlayer().hurtTime);
					event.getEntityPlayer().hurtTime = 0;
				}
				if (HURT_TIME.containsKey(event.getEntityPlayer())) {
					GlStateManager.color4f(204 / 256f, 255 / 255f, 251 / 255f, 1);
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
