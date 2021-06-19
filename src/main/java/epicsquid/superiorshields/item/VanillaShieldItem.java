package epicsquid.superiorshields.item;

import epicsquid.superiorshields.shield.GenericShieldType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class VanillaShieldItem extends SuperiorShieldItem<GenericShieldType> {

	public VanillaShieldItem(Properties props, GenericShieldType shieldType) {
		super(props, shieldType);
	}

	@Override
	protected boolean useEnergyToRecharge(ItemStack stack, PlayerEntity player) {
		stack.hurtAndBreak(1, player, e -> player.broadcastBreakEvent(EquipmentSlotType.OFFHAND));
		return !(stack.getDamageValue() >= getShield().getMaxDamage());
	}
}
