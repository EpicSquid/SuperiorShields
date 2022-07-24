package epicsquid.superiorshields.shield.effect;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.enchantment.ModEnchantments;
import epicsquid.superiorshields.lang.ModLang;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ShieldEffectMilk implements IShieldEffect {

    @Override
    public void applyEffect(@Nonnull IShieldCapability shield, @Nonnull Player player, @Nullable DamageSource source, float damage, EffectTrigger trigger, int level) {
        if (trigger == EffectTrigger.EMPTY) {
            player.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
        }
    }

    @Nonnull
    @Override
    public String getDescription() {
        return I18n.get(ModLang.CURING.getKey());
    }
}
