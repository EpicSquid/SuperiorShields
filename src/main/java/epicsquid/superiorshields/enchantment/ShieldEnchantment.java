package epicsquid.superiorshields.enchantment;

import epicsquid.superiorshields.item.ISuperiorShield;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ShieldEnchantment extends Enchantment {

	public ShieldEnchantment(Rarity rarityIn, EnchantmentCategory typeIn) {
		super(rarityIn, typeIn, new EquipmentSlot[0]);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return stack.getItem() instanceof ISuperiorShield;
	}
}
