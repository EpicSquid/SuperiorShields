package epicsquid.superiorshields.shield;

import epicsquid.superiorshields.enchantment.ModEnchantments;
import epicsquid.superiorshields.item.ISuperiorShield;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public class ShieldHelper {

	public static float getShieldCapacity(ItemStack stack) {
		if (stack.getItem() instanceof ISuperiorShield) {
			IShieldType type = ((ISuperiorShield) stack.getItem()).getShield();
			return type.getMaxShieldHp() + EnchantmentHelper.getEnchantmentLevel(ModEnchantments.CAPACITY, stack);
		}
		return 0.0f;
	}
}
