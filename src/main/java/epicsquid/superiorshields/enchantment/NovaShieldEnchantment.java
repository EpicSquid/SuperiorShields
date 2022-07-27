package epicsquid.superiorshields.enchantment;

import epicsquid.superiorshields.shield.effect.ShieldEffectNova;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import javax.annotation.Nonnull;

public class NovaShieldEnchantment extends ShieldEffectEnchantment<ShieldEffectNova> {

	public NovaShieldEnchantment(Rarity rarityIn, EnchantmentCategory typeIn, ShieldEffectNova effect) {
		super(rarityIn, typeIn, effect);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	protected boolean checkCompatibility(@Nonnull Enchantment ench) {
		return !(ench instanceof NovaShieldEnchantment);
	}
}
