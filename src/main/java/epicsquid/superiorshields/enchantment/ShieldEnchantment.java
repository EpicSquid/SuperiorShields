package epicsquid.superiorshields.enchantment;

import epicsquid.superiorshields.item.ISuperiorShield;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class ShieldEnchantment extends Enchantment {

	public ShieldEnchantment(Rarity rarityIn, EnchantmentType typeIn) {
		super(rarityIn, typeIn, new EquipmentSlotType[0]);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return stack.getItem() instanceof ISuperiorShield;
	}
}
