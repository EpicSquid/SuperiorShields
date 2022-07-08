package epicsquid.superiorshields.enchantment;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ShieldEffectEnchantment extends ShieldEnchantment {

	private final IShieldEffect effect;

	public ShieldEffectEnchantment(Rarity rarityIn, EnchantmentCategory typeIn, IShieldEffect effect) {
		super(rarityIn, typeIn);
		this.effect = effect;
	}

	public IShieldEffect getEffect() {
		return effect;
	}

}
