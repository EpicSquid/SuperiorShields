package epicsquid.superiorshields.enchantment;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class RagingEnchantment extends DamageBoostEnchantment {

    public RagingEnchantment(Rarity rarityIn, EnchantmentCategory typeIn) {
        super(rarityIn, typeIn);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public float boostDamage(float damage) {
        return damage + 2.0f;
    }

    @Override
    public boolean shouldBoostDamage(IShieldCapability shield) {
        return shield.getCurrentHp() <= 0;
    }
}
