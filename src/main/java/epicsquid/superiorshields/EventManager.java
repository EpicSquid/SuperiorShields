package epicsquid.superiorshields;

import epicsquid.superiorshields.capability.shield.ShieldCapabilityProvider;
import epicsquid.superiorshields.item.SuperiorShield;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import javax.annotation.Nonnull;

import static epicsquid.superiorshields.capability.shield.CapabilityRegistry.SHIELD_CAP_ID;

public class EventManager {

	@SubscribeEvent
	public void onLivingHurtEvent(@Nonnull LivingHurtEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CuriosApi.getCuriosHelper().getCuriosHandler(player).isPresent() && event.getSource() != DamageSource.STARVE && event.getSource() != DamageSource.DROWN) {
				var curiosOp = CuriosApi.getCuriosHelper().getCuriosHandler(player).resolve();
				if (curiosOp.isPresent()) {
					ICuriosItemHandler handler = curiosOp.get();
					handler.getStacksHandler(SuperiorShields.SHIELD_CURIO).ifPresent(
									stackHandler -> {
										ItemStack stack = stackHandler.getStacks().getStackInSlot(0);
										if (!stack.isEmpty() && stack.getItem() instanceof SuperiorShield) {
											event.setAmount(((SuperiorShield<?>) stack.getItem()).applyShield(player, stack, event.getAmount(), event.getSource()));
										}
									}
					);
				}
			}
		}
	}

	@SubscribeEvent
	public void onAttachCapabilities(@Nonnull AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof Player player) {
			var provider = new ShieldCapabilityProvider(player);
			event.addCapability(SHIELD_CAP_ID, provider);
		}
	}
}
