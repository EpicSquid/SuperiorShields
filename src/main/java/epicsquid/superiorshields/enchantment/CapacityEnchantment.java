package epicsquid.superiorshields.enchantment;

import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class CapacityEnchantment extends ShieldEnchantment {

	public CapacityEnchantment(Rarity rarityIn, EnchantmentCategory typeIn) {
		super(rarityIn, typeIn);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public int getMinCost(int pLevel) {
		return 5 + (pLevel - 1) * 8;
	}

	@Override
	public int getMaxCost(int pLevel) {
		return super.getMinCost(pLevel) + 50;
	}
}
