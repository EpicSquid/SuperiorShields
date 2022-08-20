package epicsquid.superiorshields.enchantment;

import epicsquid.superiorshields.shield.effect.ShieldEffectSpike;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import javax.annotation.Nonnull;

public class SpikeShieldEnchantment extends ShieldEffectEnchantment<ShieldEffectSpike> {

	public SpikeShieldEnchantment(Rarity rarityIn, EnchantmentCategory typeIn, ShieldEffectSpike effect) {
		super(rarityIn, typeIn, effect);
	}

	@Override
	public int getMaxLevel() {
		return 2;
	}

	@Override
	protected boolean checkCompatibility(@Nonnull Enchantment ench) {
		return !(ench instanceof SpikeShieldEnchantment);
	}

	@Override
	public int getMinCost(int pEnchantmentLevel) {
		return 5 + (pEnchantmentLevel - 1) * 9;
	}

	@Override
	public int getMaxCost(int pEnchantmentLevel) {
		return this.getMinCost(pEnchantmentLevel) + 15;
	}
}
