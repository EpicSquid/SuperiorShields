package epicsquid.superiorshields.enchantment;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public abstract class DamageBoostEnchantment extends ShieldEnchantment {

	public DamageBoostEnchantment(Rarity rarityIn, EnchantmentCategory typeIn) {
		super(rarityIn, typeIn);
	}

	@Override
	protected boolean checkCompatibility(Enchantment ench) {
		return !(ench instanceof DamageBoostEnchantment);
	}

	public abstract float boostDamage(float damage);

	public abstract boolean shouldBoostDamage(IShieldCapability shield);
}
