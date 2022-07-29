package epicsquid.superiorshields;

import epicsquid.superiorshields.capability.shield.CapabilityRegistry;
import epicsquid.superiorshields.capability.shield.ShieldCapabilityProvider;
import epicsquid.superiorshields.enchantment.DamageBoostEnchantment;
import epicsquid.superiorshields.item.ISuperiorShield;
import epicsquid.superiorshields.network.NetworkHandler;
import epicsquid.superiorshields.network.PacketShieldUpdate;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;
import top.theillusivec4.curios.api.CuriosApi;

import javax.annotation.Nonnull;

import static epicsquid.superiorshields.capability.shield.CapabilityRegistry.SHIELD_CAP_ID;

public class EventManager {

	@SubscribeEvent
	public void onLivingHurtEvent(@Nonnull LivingHurtEvent event) {
		if (event.isCanceled()) {
			return;
		}

		// handle shield buffs
		Entity attacker = event.getSource().getEntity();

		if (attacker instanceof Player player) {
			// Check if we should be amping damage
			CapabilityRegistry.getShield(player).ifPresent(shield -> {
				CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
					handler.getStacksHandler(SuperiorShields.SHIELD_CURIO).ifPresent(stackHandler -> {
										ItemStack stack = stackHandler.getStacks().getStackInSlot(0);
										if (!stack.isEmpty()) {
											for (Enchantment ench : EnchantmentHelper.getEnchantments(stack).keySet()) {
												if (ench instanceof DamageBoostEnchantment dmgEnch && dmgEnch.shouldBoostDamage(shield)) {
													event.setAmount(dmgEnch.boostDamage(event.getAmount()));
												}
											}
										}
									}
					);
				});
			});
		}

		if (event.getEntity() instanceof Player player) {
			if (event.getSource() != DamageSource.STARVE && event.getSource() != DamageSource.DROWN) {
				CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
					handler.getStacksHandler(SuperiorShields.SHIELD_CURIO).ifPresent(stackHandler -> {
										ItemStack stack = stackHandler.getStacks().getStackInSlot(0);
										if (!stack.isEmpty() && stack.getItem() instanceof ISuperiorShield superiorShield) {
											event.setAmount(superiorShield.applyShield(player, stack, event.getAmount(), event.getSource()));
										}
									}
					);
				});
			}
		}
	}

	@SubscribeEvent
	public void onAttachCapabilities(@Nonnull AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof Player) {
			var provider = new ShieldCapabilityProvider();
			event.addCapability(SHIELD_CAP_ID, provider);
		}
	}

	@SubscribeEvent
	public void entityJoinWorld(EntityJoinLevelEvent evt) {

		Entity entity = evt.getEntity();

		if (entity instanceof ServerPlayer player) {
			CapabilityRegistry.getShield(player).ifPresent(shield -> {
				NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketShieldUpdate(shield.getCurrentHp(), shield.getMaxHp()));
			});
		}
	}

}
