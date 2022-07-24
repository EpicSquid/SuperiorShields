package epicsquid.superiorshields.enchantment;

import epicsquid.superiorshields.shield.effect.ShieldEffectMilk;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class CuringEnchantment extends ShieldEffectEnchantment<ShieldEffectMilk> {

    public CuringEnchantment(Rarity rarityIn, EnchantmentCategory typeIn) {
        super(rarityIn, typeIn, new ShieldEffectMilk());
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return 1;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return super.getMinCost(enchantmentLevel) + 40;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
