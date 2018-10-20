package epicsquid.superiorshields.init;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.superiorshields.SuperiorShields;
import epicsquid.superiorshields.item.ItemSuperiorShield;
import net.minecraft.item.Item;

public class ModItems {

  // All mod items
  public static Item test_shield;

  /**
   * Register all items
   */
  public static void registerItems(@Nonnull RegisterContentEvent event) {

    event.addItem(test_shield = new ItemSuperiorShield("test_shield").setModelCustom(true).setCreativeTab(SuperiorShields.tab));
  }

  /**
   * Register item oredicts here
   */
  public static void registerOredict() {
  }
}
