package epicsquid.superiorshields.init;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.superiorshields.SuperiorShields;
import epicsquid.superiorshields.item.ItemEnergyShield;
import epicsquid.superiorshields.shield.ThermalShield;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Loader;

public class ModItems {

  // All mod items
  public static Item thermal_shield_basic, thermal_shield_hardened, thermal_shield_reinforced, thermal_shield_signalum, thermal_shield_enderium;

  /**
   * Register all items
   */
  public static void registerItems(@Nonnull RegisterContentEvent event) {

    if (Loader.isModLoaded("thermalexpansion")) {
      event.addItem(thermal_shield_basic = new ItemEnergyShield("thermal_shield_basic", ThermalShield.BASIC).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(thermal_shield_hardened = new ItemEnergyShield("thermal_shield_hardened", ThermalShield.HARDENED).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(thermal_shield_reinforced = new ItemEnergyShield("thermal_shield_reinforced", ThermalShield.REINFORCED).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(thermal_shield_signalum = new ItemEnergyShield("thermal_shield_signalum", ThermalShield.SIGNALUM).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(thermal_shield_enderium = new ItemEnergyShield("thermal_shield_enderium", ThermalShield.ENDERIUM).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
    }
  }

  /**
   * Register item oredicts here
   */
  public static void registerOredict() {
  }
}
