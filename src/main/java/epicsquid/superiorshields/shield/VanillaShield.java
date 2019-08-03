package epicsquid.superiorshields.shield;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;
import epicsquid.superiorshields.shield.effect.ShieldEffectPotionNova;
import net.minecraft.potion.Effects;

public enum VanillaShield implements IShieldType {

//  WOOD(vanilla.woodMaxHp, vanilla.woodRechargeDelay, vanilla.woodRechargeRate, vanilla.woodDamage, new ShieldEffectNone()),
//  STONE(vanilla.stoneMaxHp, vanilla.stoneRechargeDelay, vanilla.stoneRechargeRate, vanilla.stoneDamage, new ShieldEffectNone()),
  IRON(3f, 100, 50, 179, new ShieldEffectNone()),
//  GOLD(vanilla.goldMaxHp, vanilla.goldRechargeDelay, vanilla.goldRechargeRate, vanilla.goldDamage, new ShieldEffectNone()),
//  DIAMOND(vanilla.diamondMaxHp, vanilla.diamondRechargeDelay, vanilla.diamondRechargeRate, vanilla.diamondDamage, new ShieldEffectNone()),
//  OBSIDIAN(vanilla.obsidianMaxHp, vanilla.obsidianRechargeDelay, vanilla.obsidianRechargeRate, vanilla.obsidianDamage, new ShieldEffectNone()),
//  EMERALD(vanilla.emeraldMaxHp, vanilla.emeraldRechargeDelay, vanilla.emeraldRechargeRate, vanilla.emeraldDamage, new ShieldEffectNone()),
//  ENDSTONE(vanilla.endstoneMaxHp, vanilla.endstoneRechargeDelay, vanilla.endstoneRechargeRate, vanilla.endstoneDamage, new ShieldEffectPotionNova(200, 12, "shield.effect.nova.ender", Effects.GLOWING, Effects.LEVITATION))

  ;

  private float maxHp;
  private int shieldRechargeDelay;
  private int shieldRechargeRate;
  private int maxDamage;
  private IShieldEffect effect;

  VanillaShield(float maxHp, int shieldRechargeDelay, int shieldRechargeRate, int maxDamage, IShieldEffect effect) {
    this.maxHp = maxHp;
    this.shieldRechargeDelay = shieldRechargeDelay;
    this.shieldRechargeRate = shieldRechargeRate;
    this.maxDamage = maxDamage;
    this.effect = effect;
  }

  public int getMaxDamage() {
    return maxDamage;
  }

  @Override
  public float getMaxShieldHp() {
    return maxHp;
  }

  @Override
  public int getShieldRechargeDelay() {
    return shieldRechargeDelay;
  }

  @Override
  public int getShieldRechargeRate() {
    return shieldRechargeRate;
  }

  @Override
  public int getColor() {
    return 0;
  }

  @Nonnull
  @Override
  public IShieldEffect getEffect() {
    return effect;
  }
}
