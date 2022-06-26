package epicsquid.superiorshields;

import epicsquid.superiorshields.enchantment.CapacityEnchantment;
import epicsquid.superiorshields.enchantment.JumpStartEnchantment;
import epicsquid.superiorshields.enchantment.QuickenedEnchantment;
import epicsquid.superiorshields.item.SuperiorShield;
import epicsquid.superiorshields.item.VanillaShieldItem;
import epicsquid.superiorshields.shield.GenericShieldType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SuperiorShields.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryManager {

    public static final EnchantmentCategory type = EnchantmentCategory.create("superior_shield", item -> item instanceof SuperiorShield);

    private static final GenericShieldType IRON = new GenericShieldType(5f, 80, 40, Tiers.IRON.getUses(), Tiers.IRON.getEnchantmentValue());
    private static final GenericShieldType GOLD = new GenericShieldType(3f, 20, 40, Tiers.GOLD.getUses(), Tiers.GOLD.getEnchantmentValue());
    private static final GenericShieldType DIAMOND = new GenericShieldType(7f, 60, 40, Tiers.DIAMOND.getUses(), Tiers.DIAMOND.getEnchantmentValue());

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new VanillaShieldItem(new Item.Properties().stacksTo(1).tab(SuperiorShields.ITEM_GROUP).durability(Tiers.IRON.getUses()), IRON).setRegistryName(SuperiorShields.MODID, "iron_shield"));
        event.getRegistry().register(new VanillaShieldItem(new Item.Properties().stacksTo(1).tab(SuperiorShields.ITEM_GROUP).durability(Tiers.GOLD.getUses()), GOLD).setRegistryName(SuperiorShields.MODID, "golden_shield"));
        event.getRegistry().register(new VanillaShieldItem(new Item.Properties().stacksTo(1).tab(SuperiorShields.ITEM_GROUP).durability(Tiers.DIAMOND.getUses()), DIAMOND).setRegistryName(SuperiorShields.MODID, "diamond_shield"));
    }

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {

        event.getRegistry().register(new CapacityEnchantment(Enchantment.Rarity.COMMON, type).setRegistryName(SuperiorShields.MODID, "capacity"));
        event.getRegistry().register(new QuickenedEnchantment(Enchantment.Rarity.COMMON, type).setRegistryName(SuperiorShields.MODID, "quickened"));
        event.getRegistry().register(new JumpStartEnchantment(Enchantment.Rarity.RARE, type).setRegistryName(SuperiorShields.MODID, "jump_start"));
    }
}
