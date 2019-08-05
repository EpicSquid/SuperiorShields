package epicsquid.superiorshields.shield;

import epicsquid.superiorshields.enchantment.ModEnchantments;
import epicsquid.superiorshields.item.SuperiorShield;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public class ShieldHelper {

	public static float getShieldCapacity(ItemStack stack) {
		if (stack.getItem() instanceof SuperiorShield) {
			ShieldType type = ((SuperiorShield) stack.getItem()).getShield();
			return type.getMaxShieldHp() + EnchantmentHelper.getEnchantmentLevel(ModEnchantments.CAPACITY, stack);
		}
		return 0.0f;
	}

	public static int getShieldRechargeRate(ItemStack stack) {
		if (stack.getItem() instanceof SuperiorShield) {
			ShieldType type = ((SuperiorShield) stack.getItem()).getShield();
			return type.getShieldRechargeRate() - (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.QUICKENED, stack) * type.getShieldRechargeRate() / 5);
		}
		return 0;
	}
}
