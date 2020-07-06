package epicsquid.superiorshields.item;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.shield.ShieldType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public interface SuperiorShield<T extends ShieldType> {

	float applyShield(PlayerEntity player, ItemStack stack, float damage, DamageSource source);

	void rechargeShield(IShieldCapability shield, ItemStack stack, PlayerEntity player);

	void resetShieldDelay(IShieldCapability shield);

	T getShield();

	void unequip(PlayerEntity player);

	void equip(PlayerEntity player, ItemStack stack);
}
