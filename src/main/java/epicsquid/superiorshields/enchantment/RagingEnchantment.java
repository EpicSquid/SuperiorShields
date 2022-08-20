package epicsquid.superiorshields.enchantment;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.config.Config;
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
		return damage + Config.SHIELD.RAGING_DAMAGE_ADDED.get().floatValue();
	}

	@Override
	public boolean shouldBoostDamage(IShieldCapability shield) {
		return shield.getCurrentHp() <= 0;
	}

	@Override
	public int getMinCost(int pEnchantmentLevel) {
		return 1;
	}

	@Override
	public int getMaxCost(int pEnchantmentLevel) {
		return this.getMinCost(pEnchantmentLevel) + 40;
	}
}
