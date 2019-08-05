package epicsquid.superiorshields.enchantment;

import net.minecraft.enchantment.EnchantmentType;

public class CapacityEnchantment extends ShieldEnchantment {

	public CapacityEnchantment(Rarity rarityIn, EnchantmentType typeIn) {
		super(rarityIn, typeIn);
	}

	@Override
	public int getMaxLevel() {
		return 4;
	}
}
