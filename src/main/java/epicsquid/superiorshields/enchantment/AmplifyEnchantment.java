package epicsquid.superiorshields.enchantment;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AmplifyEnchantment extends DamageBoostEnchantment {

    public AmplifyEnchantment(Rarity rarityIn, EnchantmentCategory typeIn) {
        super(rarityIn, typeIn);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public float boostDamage(float damage) {
        return damage * 2.0f;
    }

    @Override
    public boolean shouldBoostDamage(IShieldCapability shield) {
        if (shield.getCurrentHp() >= shield.getMaxHp() && shield.getMaxHp() > 0) {
            shield.setCurrentHp(shield.getCurrentHp() - 1.5f);
            shield.setTimeWithoutDamage(0);
            return true;
        }
        return false;
    }
}
