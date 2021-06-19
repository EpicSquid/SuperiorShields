package epicsquid.superiorshields.enchantment;

import net.minecraft.enchantment.EnchantmentType;

public class JumpStartEnchantment extends ShieldEnchantment {

	public JumpStartEnchantment(Rarity rarityIn, EnchantmentType typeIn) {
		super(rarityIn, typeIn);
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 15;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return super.getMinCost(enchantmentLevel) + 50;
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}
}
