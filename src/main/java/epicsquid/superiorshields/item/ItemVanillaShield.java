package epicsquid.superiorshields.item;

import epicsquid.superiorshields.shield.GenericShieldType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class ItemVanillaShield extends ItemSuperiorShield<GenericShieldType> {

	public ItemVanillaShield(Properties props, GenericShieldType shieldType) {
		super(props, shieldType);
	}

	@Override
	protected boolean useEnergyToRecharge(ItemStack stack, PlayerEntity player) {
		stack.damageItem(1, player, e -> player.sendBreakAnimation(EquipmentSlotType.OFFHAND));
		return !(stack.getDamage() >= shieldType.getMaxDamage());
	}
}
