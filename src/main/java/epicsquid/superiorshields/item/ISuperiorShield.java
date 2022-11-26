package epicsquid.superiorshields.item;

import epicsquid.superiorshields.capability.shield.CapabilityRegistry;
import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.enchantment.EnchantmentUtils;
import epicsquid.superiorshields.network.NetworkHandler;
import epicsquid.superiorshields.network.PacketShieldUpdate;
import epicsquid.superiorshields.setup.compat.ArsCompat;
import epicsquid.superiorshields.shield.IShieldType;
import epicsquid.superiorshields.shield.effect.EffectTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface ISuperiorShield<T extends IShieldType> {

	default float applyShield(Player player, ItemStack stack, float damage, DamageSource source) {
		var shieldOp = CapabilityRegistry.getShield(player).resolve();
		if (shieldOp.isPresent()) {
			IShieldCapability shield = shieldOp.get();
			if (shield.getCurrentHp() > 0) {
				triggerShieldEffect(player, stack, source, damage, EffectTrigger.DAMAGE);
				if (damage >= shield.getCurrentHp()) {
					triggerShieldEffect(player, stack, source, damage, EffectTrigger.EMPTY);
				}
			}
			return absorbDamage(player, shield, shield.getCurrentHp() - damage);
		}
		return damage;
	}

	default float absorbDamage(Player player, IShieldCapability shield, float absorbed) {
		shield.setCurrentHp(absorbed);
		resetShieldDelay(shield);
		updateClient(player, shield);
		return absorbed < 0f ? -1f * absorbed : 0f;
	}

	default void triggerShieldEffect(Player player, ItemStack stack, @Nullable DamageSource source, float damage, EffectTrigger trigger) {
		CapabilityRegistry.getShield(player).ifPresent(shield -> {
			// Always apply at level 1 when the effect is on the shield, not on the enchantment
			getShield().getEffect().applyEffect(shield, player, source, damage, trigger, 1);
			EnchantmentUtils.triggerEnchantmentEffect(shield, player, stack, source, damage, trigger);
			// Ars Reactive Compat
			ArsCompat.reactiveTrigger(stack, player, trigger);
		});
	}

	default void updateClient(Player player, IShieldCapability shield) {
		NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new PacketShieldUpdate(shield.getCurrentHp(), shield.getMaxHp()));
	}

	default void rechargeShield(@Nonnull IShieldCapability shield, ItemStack stack, Player player) {
		if (shield.getCurrentHp() < shield.getMaxHp()) {
			if (useEnergyToRecharge(stack, player)) {
				shield.setCurrentHp(shield.getCurrentHp() + 1.0f);
			}
		}
	}

	default void resetShieldDelay(@Nonnull IShieldCapability shield) {
		shield.setTimeWithoutDamage(0);
	}

	T getShield();

	default float getShieldCapacity(@Nonnull ItemStack stack) {
		return getShield().getCapacity();
	}

	default int getShieldDelay(@Nonnull ItemStack stack) {
		return getShield().getDelay();
	}

	default int getShieldRate(@Nonnull ItemStack stack) {
		return getShield().getRate();
	}

	/**
	 * Triggers the use of energy to recharge.
	 *
	 * @param stack The stack to get the capability to recharge from.
	 */
	default boolean useEnergyToRecharge(ItemStack stack, Player player) {
		return true;
	}
}
