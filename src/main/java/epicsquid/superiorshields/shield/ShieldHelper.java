package epicsquid.superiorshields.shield;

import epicsquid.superiorshields.config.Config;
import epicsquid.superiorshields.enchantment.ModEnchantments;
import epicsquid.superiorshields.item.ISuperiorShield;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class ShieldHelper {

	public static float getShieldCapacity(ItemStack stack) {
		if (stack.getItem() instanceof ISuperiorShield<?> shieldItem) {
			return shieldItem.getShieldCapacity(stack) + (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.CAPACITY.get(), stack) * Config.SHIELD.SHIELD_CAPACITY_INCREASE.get().floatValue());
		}
		return 0.0f;
	}

	public static int getShieldRechargeRate(ItemStack stack) {
		if (stack.getItem() instanceof ISuperiorShield<?> shieldItem) {
			IShieldType type = shieldItem.getShield();
			return shieldItem.getShieldRate(stack) - (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.QUICKENED.get(), stack) * type.getRate() / Config.SHIELD.QUICKEN_RATE.get());
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
