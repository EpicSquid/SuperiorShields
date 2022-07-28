package epicsquid.superiorshields.enchantment;

import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class QuickenedEnchantment extends ShieldEnchantment {

	public QuickenedEnchantment(Rarity rarityIn, EnchantmentCategory typeIn) {
		super(rarityIn, typeIn);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}
}
