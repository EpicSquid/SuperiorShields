package epicsquid.superiorshields.init;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.superiorshields.SuperiorShields;
import epicsquid.superiorshields.item.ItemEnergyShield;
import epicsquid.superiorshields.shield.EnderIOShield;
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

    if (Loader.isModLoaded("enderio")) {
      event.addItem(new ItemEnergyShield("enderio_ds_shield_conductive_iron", EnderIOShield.DS_CONDUCTIVE_IRON).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(new ItemEnergyShield("enderio_es_shield_conductive_iron", EnderIOShield.ES_CONDUCTIVE_IRON).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(new ItemEnergyShield("enderio_ds_shield_energetic_alloy", EnderIOShield.DS_ENERGETIC_ALLOY).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(new ItemEnergyShield("enderio_es_shield_energetic_alloy", EnderIOShield.ES_ENERGETIC_ALLOY).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(new ItemEnergyShield("enderio_ds_shield_vibrant_alloy", EnderIOShield.DS_VIBRANT_ALLOY).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(new ItemEnergyShield("enderio_es_shield_vibrant_alloy", EnderIOShield.ES_VIBRANT_ALLOY).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(new ItemEnergyShield("enderio_ds_shield_pulsating_iron", EnderIOShield.DS_PULSATING_IRON).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(new ItemEnergyShield("enderio_es_shield_pulsating_iron", EnderIOShield.ES_PULSATING_IRON).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(new ItemEnergyShield("enderio_ds_shield_redstone_alloy", EnderIOShield.DS_REDSTONE_ALLOY).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(new ItemEnergyShield("enderio_es_shield_redstone_alloy", EnderIOShield.ES_REDSTONE_ALLOY).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(new ItemEnergyShield("enderio_shield_soularium", EnderIOShield.SOULARIUM).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
    }

    if (Loader.isModLoaded("enderioendergy")) {
      event.addItem(new ItemEnergyShield("endergy_ds_shield_energetic_silver", EnderIOShield.DS_ENERGETIC_SILVER).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(new ItemEnergyShield("endergy_es_shield_energetic_silver", EnderIOShield.ES_ENERGETIC_SILVER).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(new ItemEnergyShield("endergy_ds_shield_vivid_alloy", EnderIOShield.DS_VIVID_ALLOY).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(new ItemEnergyShield("endergy_es_shield_vivid_alloy", EnderIOShield.ES_VIVID_ALLOY).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(new ItemEnergyShield("endergy_ds_shield_crystalline_alloy", EnderIOShield.DS_CRYSTALLINE_ALLOY).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(new ItemEnergyShield("endergy_es_shield_crystalline_alloy", EnderIOShield.ES_CRYSTALLINE_ALLOY).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(new ItemEnergyShield("endergy_ds_shield_melodic_alloy", EnderIOShield.DS_MELODIC_ALLOY).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(new ItemEnergyShield("endergy_es_shield_melodic_alloy", EnderIOShield.ES_MELODIC_ALLOY).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
      event.addItem(new ItemEnergyShield("endergy_shield_stellar_alloy", EnderIOShield.STELLAR_ALLOY).setModelCustom(true).setCreativeTab(SuperiorShields.tab));
    }
  }

  /**
   * Register item oredicts here
   */
  public static void registerOredict() {
  }
}
