package epicsquid.superiorshields;

import epicsquid.superiorshields.item.ItemVanillaShield;
import epicsquid.superiorshields.shield.VanillaShield;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SuperiorShields.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryManager {

  @SubscribeEvent
  public static void registerItems(RegistryEvent.Register<Item> event) {
    event.getRegistry().register(new ItemVanillaShield(new Item.Properties().maxStackSize(1).group(SuperiorShields.ITEM_GROUP).maxDamage(179), VanillaShield.IRON).setRegistryName(SuperiorShields.MODID, "vanilla_shield_iron"));
  }
}
