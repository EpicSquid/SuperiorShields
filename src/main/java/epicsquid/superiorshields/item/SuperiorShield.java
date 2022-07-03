package epicsquid.superiorshields.item;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.shield.ShieldType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface SuperiorShield<T extends ShieldType> {

	float applyShield(Player player, ItemStack stack, float damage, DamageSource source);

	void rechargeShield(IShieldCapability shield, ItemStack stack, Player player);

	void resetShieldDelay(IShieldCapability shield);

	T getShield();

	/**
	 * Triggers the use of energy to recharge.
	 *
	 * @param stack The stack to get the capability to recharge from.
	 */
	default boolean useEnergyToRecharge(ItemStack stack, Player player) {
		return true;
	}
}
