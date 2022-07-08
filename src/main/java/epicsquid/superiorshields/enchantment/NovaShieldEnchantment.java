package epicsquid.superiorshields.enchantment;

import epicsquid.superiorshields.item.SuperiorShield;
import epicsquid.superiorshields.shield.effect.IShieldEffect;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class NovaShieldEnchantment extends ShieldEffectEnchantment {

    public NovaShieldEnchantment(Rarity rarityIn, EnchantmentCategory typeIn, IShieldEffect effect) {
        super(rarityIn, typeIn, effect);
    }

    @Override
    protected boolean checkCompatibility(@Nonnull Enchantment ench) {
        return !(ench instanceof NovaShieldEnchantment);
    }
}
