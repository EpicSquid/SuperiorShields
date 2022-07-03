package epicsquid.superiorshields;

import com.mojang.blaze3d.systems.RenderSystem;
import epicsquid.superiorshields.capability.shield.CapabilityRegistry;
import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.gui.GuiShieldOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;

public class ClientEventManager {

	private static final GuiShieldOverlay shieldHUD = new GuiShieldOverlay();

	private final HashMap<Player, Integer> HURT_TIME = new HashMap<>();

	@SubscribeEvent
	public void onRenderPlayer(RenderPlayerEvent.Pre event) {
		Player player = event.getPlayer();
		var shieldOp = CapabilityRegistry.getShield(player).resolve();
		if (shieldOp.isPresent()) {
			IShieldCapability shield = shieldOp.get();
			if (shield.getCurrentHp() > 0 && player.getLastDamageSource() != DamageSource.STARVE && player.getLastDamageSource() != DamageSource.DROWN) {
				if (event.getPlayer().hurtTime > 0) {
					HURT_TIME.put(event.getPlayer(), event.getPlayer().hurtTime);
					event.getPlayer().hurtTime = 0;
				}
				if (HURT_TIME.containsKey(event.getPlayer())) {
					RenderSystem.clearColor(204 / 256f, 255 / 255f, 251 / 255f, 1);
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
}
