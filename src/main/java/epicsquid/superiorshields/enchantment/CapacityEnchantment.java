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
}
