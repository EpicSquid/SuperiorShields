package epicsquid.superiorshields.item;

import epicsquid.superiorshields.enchantment.ModEnchantments;
import epicsquid.superiorshields.shield.IShieldType;
import epicsquid.superiorshields.shield.VanillaShield;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class VanillaShieldItem extends SuperiorShieldItem<IShieldType> {

    public VanillaShieldItem(Item.Properties props, IShieldType shieldType) {
        super(props, shieldType);
    }

    @Override
    public boolean useEnergyToRecharge(ItemStack stack, Player player) {
        stack.hurtAndBreak(1, player, e -> player.broadcastBreakEvent(EquipmentSlot.OFFHAND));
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.category.equals(ModEnchantments.type) || enchantment.equals(Enchantments.UNBREAKING) || enchantment.equals(Enchantments.MENDING);
    }
}
