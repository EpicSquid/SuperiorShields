package epicsquid.superiorshields.enchantment;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ShieldEffectEnchantment<T extends IShieldEffect> extends ShieldEnchantment {

	private final T effect;

	public ShieldEffectEnchantment(Rarity rarityIn, EnchantmentCategory typeIn, T effect) {
		super(rarityIn, typeIn);
		this.effect = effect;
	}

	public T getEffect() {
		return effect;
	}

}
