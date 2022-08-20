package epicsquid.superiorshields.enchantment;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.config.Config;
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
			shield.setCurrentHp(shield.getCurrentHp() - Config.SHIELD.AMPLIFY_SHIELD_DRAIN.get());
			shield.setTimeWithoutDamage(0);
			return true;
		}
		return false;
	}

	@Override
	public int getMinCost(int pEnchantmentLevel) {
		return 15;
	}

	@Override
	public int getMaxCost(int pEnchantmentLevel) {
		return super.getMinCost(pEnchantmentLevel) + 50;
	}
}
