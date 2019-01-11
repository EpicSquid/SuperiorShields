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
}
