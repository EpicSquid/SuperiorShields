package epicsquid.superiorshields.item;

import epicsquid.superiorshields.shield.IShieldType;
import epicsquid.superiorshields.shield.VanillaShield;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class VanillaShieldItem extends SuperiorShieldItem<IShieldType> {

    public VanillaShieldItem(Item.Properties props, IShieldType shieldType) {
        super(props, shieldType);
    }

    @Override
    public boolean useEnergyToRecharge(ItemStack stack, Player player) {
        stack.hurtAndBreak(1, player, e -> player.broadcastBreakEvent(EquipmentSlot.OFFHAND));
        return true;
    }
}
