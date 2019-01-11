package epicsquid.superiorshields.config;

import epicsquid.superiorshields.SuperiorShields;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.Mod;

@Config(modid = SuperiorShields.MODID)
@Mod.EventBusSubscriber(modid = SuperiorShields.MODID)
public class ConfigManager {

  @Config.Comment(("Config for Vanilla Shield stats and enabled status"))
  public static ConfigVanilla vanilla = new ConfigVanilla();

  @Config.Comment(("Config for Botania Shield stats and enabled status"))
  public static ConfigBotania botania = new ConfigBotania();

  @Config.Comment(("Config for Ender IO Shield stats and enabled status"))
  public static ConfigEnderIO enderio = new ConfigEnderIO();

  @Config.Comment(("Config for Endergy Shield stats and enabled status"))
  public static ConfigEndergy endergy = new ConfigEndergy();

  @Config.Comment(("Config for Nature's Aura Shield stats and enabled status"))
  public static ConfigNaturesAura naturesAura = new ConfigNaturesAura();

  @Config.Comment(("Config for Thermal Expansion Shield stats and enabled status"))
  public static ConfigThermal thermal = new ConfigThermal();

  public static class ConfigVanilla {

    public boolean enableVanillaShields = true;

    public boolean woodEnabled = true;
    public float woodMaxHp = 1f;
    public int woodRechargeDelay = 100;
    public int woodRechargeRate = 100;
    public int woodDamage = 40;

    public boolean stoneEnabled = true;
    public float stoneMaxHp = 3f;
    public int stoneRechargeDelay = 100;
    public int stoneRechargeRate = 50;
    public int stoneDamage = 80;

    public boolean ironEnabled = true;
    public float ironMaxHp = 6f;
    public int ironRechargeDelay = 80;
    public int ironRechargeRate = 40;
    public int ironDamage = 200;

    public boolean goldEnabled = true;
    public float goldMaxHp = 4f;
    public int goldRechargeDelay = 20;
    public int goldRechargeRate = 10;
    public int goldDamage = 120;

    public boolean diamondEnabled = true;
    public float diamondMaxHp = 9f;
    public int diamondRechargeDelay = 60;
    public int diamondRechargeRate = 40;
    public int diamondDamage = 500;

    public boolean obsidianEnabled = true;
    public float obsidianMaxHp = 12f;
    public int obsidianRechargeDelay = 100;
    public int obsidianRechargeRate = 60;
    public int obsidianDamage = 400;

    public boolean emeraldEnabled = true;
    public float emeraldMaxHp = 7f;
    public int emeraldRechargeDelay = 30;
    public int emeraldRechargeRate = 15;
    public int emeraldDamage = 240;

    public boolean endstoneEnabled = true;
    public float endstoneMaxHp = 10f;
    public int endstoneRechargeDelay = 80;
    public int endstoneRechargeRate = 40;
    public int endstoneDamage = 360;
  }

  public static class ConfigBotania {

    public boolean enableBotaniaShields = true;

    public boolean manaSteelEnabled = true;
    public float manaSteelMaxHp = 4f;
    public int manaSteelRechargeDelay = 40;
    public int manaSteelRechargeRate = 15;

    public boolean terraSteelEnabled = true;
    public float terraSteelMaxHp = 8f;
    public int terraSteelRechargeDelay = 30;
    public int terraSteelRechargeRate = 10;

    public boolean elementiumEnabled = true;
    public float elementiumMaxHp = 6f;
    public int elementiumRechargeDelay = 15;
    public int elementiumRechargeRate = 10;
  }

  public static class ConfigEnderIO {

    public boolean enableEnderIOShields = true;

    public boolean darkSteelConductiveIronEnabled = true;
    public float darkSteelConductiveIronMaxHp = 3f;
    public int darkSteelConductiveIronRechargeDelay = 40;
    public int darkSteelConductiveIronRechargeRate = 10;
    public int darkSteelConductiveIronEnergy = 60000;

    public boolean darkSteelEnergeticAlloyEnabled = true;
    public float darkSteelEnergeticAlloyMaxHp = 5f;
    public int darkSteelEnergeticAlloyRechargeDelay = 40;
    public int darkSteelEnergeticAlloyRechargeRate = 10;
    public int darkSteelEnergeticAlloyEnergy = 120000;

    public boolean darkSteelVibrantAlloyEnabled = true;
    public float darkSteelVibrantAlloyMaxHp = 8f;
    public int darkSteelVibrantAlloyRechargeDelay = 30;
    public int darkSteelVibrantAlloyRechargeRate = 10;
    public int darkSteelVibrantAlloyEnergy = 240000;

    public boolean endSteelConductiveIronEnabled = true;
    public float endSteelConductiveIronMaxHp = 5f;
    public int endSteelConductiveIronRechargeDelay = 40;
    public int endSteelConductiveIronRechargeRate = 10;
    public int endSteelConductiveIronEnergy = 120000;

    public boolean endSteelEnergeticAlloyEnabled = true;
    public float endSteelEnergeticAlloyMaxHp = 7f;
    public int endSteelEnergeticAlloyRechargeDelay = 40;
    public int endSteelEnergeticAlloyRechargeRate = 10;
    public int endSteelEnergeticAlloyEnergy = 240000;

    public boolean endSteelVibrantAlloyEnabled = true;
    public float endSteelVibrantAlloyMaxHp = 10f;
    public int endSteelVibrantAlloyRechargeDelay = 30;
    public int endSteelVibrantAlloyRechargeRate = 10;
    public int endSteelVibrantAlloyEnergy = 480000;

    public boolean soulariumEnabled = true;
    public float soulariumMaxHp = 10f;
    public int soulariumRechargeDelay = 200;
    public int soulariumRechargeRate = 40;
    public int soulariumEnergy = 480000;
  }

  public static class ConfigEndergy {

    public boolean enableEndergyShields = true;

    public boolean darkSteelEnergeticSilverEnabled = true;
    public float darkSteelEnergeticSilverMaxHp = 5f;
    public int darkSteelEnergeticSilverRechargeDelay = 40;
    public int darkSteelEnergeticSilverRechargeRate = 10;
    public int darkSteelEnergeticSilverEnergy = 120000;

    public boolean darkSteelVividAlloyEnabled = true;
    public float darkSteelVividAlloyMaxHp = 8f;
    public int darkSteelVividAlloyRechargeDelay = 30;
    public int darkSteelVividAlloyRechargeRate = 10;
    public int darkSteelVividAlloyEnergy = 240000;

    public boolean darkSteelCrystallineAlloyEnabled = true;
    public float darkSteelCrystallineAlloyMaxHp = 10f;
    public int darkSteelCrystallineAlloyRechargeDelay = 30;
    public int darkSteelCrystallineAlloyRechargeRate = 10;
    public int darkSteelCrystallineAlloyEnergy = 512000;

    public boolean darkSteelMelodicAlloyEnabled = true;
    public float darkSteelMelodicAlloyMaxHp = 12f;
    public int darkSteelMelodicAlloyRechargeDelay = 25;
    public int darkSteelMelodicAlloyRechargeRate = 10;
    public int darkSteelMelodicAlloyEnergy = 1024000;

    public boolean endSteelEnergeticSilverEnabled = true;
    public float endSteelEnergeticSilverMaxHp = 7f;
    public int endSteelEnergeticSilverRechargeDelay = 40;
    public int endSteelEnergeticSilverRechargeRate = 10;
    public int endSteelEnergeticSilverEnergy = 240000;

    public boolean endSteelVividAlloyEnabled = true;
    public float endSteelVividAlloyMaxHp = 10f;
    public int endSteelVividAlloyRechargeDelay = 30;
    public int endSteelVividAlloyRechargeRate = 10;
    public int endSteelVividAlloyEnergy = 480000;

    public boolean endSteelCrystallineAlloyEnabled = true;
    public float endSteelCrystallineAlloyMaxHp = 12f;
    public int endSteelCrystallineAlloyRechargeDelay = 30;
    public int endSteelCrystallineAlloyRechargeRate = 10;
    public int endSteelCrystallineAlloyEnergy = 1024000;

    public boolean endSteelMelodicAlloyEnabled = true;
    public float endSteelMelodicAlloyMaxHp = 14f;
    public int endSteelMelodicAlloyRechargeDelay = 25;
    public int endSteelMelodicAlloyRechargeRate = 10;
    public int endSteelMelodicAlloyEnergy = 2048000;

    public boolean stellarAlloyEnabled = true;
    public float stellarAlloyMaxHp = 20f;
    public int stellarAlloyRechargeDelay = 20;
    public int stellarAlloyRechargeRate = 10;
    public int stellarAlloyEnergy = 4096000;
  }

  public static class ConfigNaturesAura{

    public boolean enableNaturesAuraShields = true;

    public boolean infusedIronEnabled = true;
    public float infusedIronMaxHp = 9f;
    public int infusedIronRechargeDelay = 200;
    public int infusedIronRechargeRate = 40;

    public boolean skyIngotEnabled = true;
    public float skyIngotMaxHp = 14f;
    public int skyIngotRechargeDelay = 200;
    public int skyIngotRechargeRate = 30;

  }

  public static class ConfigThermal {

    public boolean enabledThermalShields = true;

    public boolean basicEnabled = true;
    public float basicMaxHp = 3f;
    public int basicRechargeDelay = 12;
    public int basicRechargeRate = 40;
    public int basicEnergy = 40000;

    public boolean hardenedEnabled = true;
    public float hardenedMaxHp = 6f;
    public int hardenedRechargeDelay = 10;
    public int hardenedRechargeRate = 40;
    public int hardenedEnergy = 120000;

    public boolean reinforcedEnabled = true;
    public float reinforcedMaxHp = 9f;
    public int reinforcedRechargeDelay = 8;
    public int reinforcedRechargeRate = 40;
    public int reinforcedEnergy = 240000;

    public boolean signalumEnabled = true;
    public float signalumMaxHp = 12f;
    public int signalumRechargeDelay = 7;
    public int signaulmRechargeRate = 30;
    public int signalumEnergy = 400000;

    public boolean enderiumEnabled = true;
    public float enderiumMaxHp = 15f;
    public int enderiumRechargeDelay = 6;
    public int enderiumRechargeRate = 30;
    public int enderiumEnergy = 600000;

    public boolean pyrotheumEnabled = true;
    public float pyrotheumMaxHp = 9f;
    public int pyrotheumRechargeDelay = 60;
    public int pyrotheumRechargeRate = 20;
    public int pyrotheumEnergy = 240000;

    public boolean cryotheumEnabled = true;
    public float cryotheumMaxHp = 9f;
    public int cryotheumRechargeDelay = 60;
    public int cryotheumRechargeRate = 20;
    public int cryotheumEnergy = 240000;

    public boolean aerotheumEnabled = true;
    public float aerotheumMaxHp = 9f;
    public int aerotheumRechargeDelay = 60;
    public int aerotheumRechargeRate = 20;
    public int aerotheumEnergy = 240000;

    public boolean petrotheumEnabled = true;
    public float petrotheumMaxHp = 9f;
    public int petrotheumRechargeDelay = 60;
    public int petrotheumRechargeRate = 20;
    public int petrotheumEnergy = 240000;

  }
}
