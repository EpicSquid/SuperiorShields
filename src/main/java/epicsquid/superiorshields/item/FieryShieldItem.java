package epicsquid.superiorshields.item;

import epicsquid.superiorshields.enchantment.ModEnchantments;
import epicsquid.superiorshields.shield.IShieldType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class FieryShieldItem extends VanillaShieldItem {


	public FieryShieldItem(Properties props, IShieldType shieldType) {
		super(props, shieldType);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return super.canApplyAtEnchantingTable(stack, enchantment) && !enchantment.equals(ModEnchantments.FIRE_NOVA.get());
	}
}
