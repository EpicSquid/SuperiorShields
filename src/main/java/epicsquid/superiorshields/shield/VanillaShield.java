package epicsquid.superiorshields.shield;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.shield.effect.IShieldEffect;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;
import epicsquid.superiorshields.shield.effect.ShieldEffectPotionNova;
import net.minecraft.init.MobEffects;

public enum VanillaShield implements IShieldType {

  WOOD(1f, 100, 100, 40, new ShieldEffectNone()),
  STONE(3f, 100, 50, 80, new ShieldEffectNone()),
  IRON(6f, 80, 40, 200, new ShieldEffectNone()),
  GOLD(4f, 20, 10, 120, new ShieldEffectNone()),
  DIAMOND(9f, 60, 40, 500, new ShieldEffectNone()),
  OBSIDIAN(12f, 100, 60, 400, new ShieldEffectNone()),
  EMERALD(7f, 30, 15, 240, new ShieldEffectNone()),
  ENDSTONE(10f, 80, 40, 360, new ShieldEffectPotionNova(200, 12, MobEffects.GLOWING, MobEffects.LEVITATION))

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
