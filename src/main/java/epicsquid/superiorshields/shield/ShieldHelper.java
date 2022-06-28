package epicsquid.superiorshields.shield;

import epicsquid.superiorshields.enchantment.ModEnchantments;
import epicsquid.superiorshields.item.SuperiorShield;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class ShieldHelper {

	public static float getShieldCapacity(ItemStack stack) {
		if (stack.getItem() instanceof SuperiorShield) {
			ShieldType type = ((SuperiorShield<?>) stack.getItem()).getShield();
			return type.getMaxShieldHp() + EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.CAPACITY.get(), stack);
		}
		return 0.0f;
	}

	public static int getShieldRechargeRate(ItemStack stack) {
		if (stack.getItem() instanceof SuperiorShield) {
			ShieldType type = ((SuperiorShield<?>) stack.getItem()).getShield();
			return type.getShieldRechargeRate() - (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.QUICKENED.get(), stack) * type.getShieldRechargeRate() / 5);
		}
		return 0;
	}
}
