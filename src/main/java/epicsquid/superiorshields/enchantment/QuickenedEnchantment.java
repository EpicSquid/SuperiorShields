package epicsquid.superiorshields.enchantment;

import net.minecraft.enchantment.EnchantmentType;

public class QuickenedEnchantment extends ShieldEnchantment {

	public QuickenedEnchantment(Rarity rarityIn, EnchantmentType typeIn) {
		super(rarityIn, typeIn);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}
}
