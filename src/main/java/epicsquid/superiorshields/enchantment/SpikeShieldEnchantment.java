package epicsquid.superiorshields.enchantment;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
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
}
