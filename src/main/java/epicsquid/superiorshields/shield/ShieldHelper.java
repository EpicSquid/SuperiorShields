package epicsquid.superiorshields.shield;

import epicsquid.superiorshields.enchantment.ModEnchantments;
import epicsquid.superiorshields.item.ISuperiorShield;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class ShieldHelper {

	public static float getShieldCapacity(ItemStack stack) {
		if (stack.getItem() instanceof ISuperiorShield<?> shieldItem) {
			return shieldItem.getShieldCapacity(stack) + (EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.CAPACITY.get(), stack) * 2);
		}
		return 0.0f;
	}

	public static int getShieldRechargeRate(ItemStack stack) {
		if (stack.getItem() instanceof ISuperiorShield<?> shieldItem) {
			IShieldType type = shieldItem.getShield();
			return shieldItem.getShieldRate(stack) - (EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.QUICKENED.get(), stack) * type.getRate() / 5);
		}
		return 0;
	}

	public static int getShieldRechargeDelay(ItemStack stack) {
		if (stack.getItem() instanceof ISuperiorShield<?> shieldItem) {
			return shieldItem.getShieldDelay(stack);
		}
		return 0;
	}
}
