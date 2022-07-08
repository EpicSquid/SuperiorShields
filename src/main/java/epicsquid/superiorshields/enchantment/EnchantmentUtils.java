package epicsquid.superiorshields.enchantment;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.shield.effect.EffectTrigger;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import javax.annotation.Nullable;

public class EnchantmentUtils {

	public static void triggerEnchantmentEffect(IShieldCapability shield, Player player, ItemStack stack, @Nullable DamageSource source, float damage, EffectTrigger trigger) {
		for (Enchantment enchantment: EnchantmentHelper.getEnchantments(stack).keySet()) {
			if (enchantment instanceof ShieldEffectEnchantment effectEnchantment) {
				effectEnchantment.getEffect().applyEffect(shield, player, source, damage, trigger, EnchantmentHelper.getItemEnchantmentLevel(enchantment, stack));
			}
		}
	}
}
