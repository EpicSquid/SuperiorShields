package epicsquid.superiorshields.shield.effect;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ShieldEffectNone implements IShieldEffect {

    @Override
    public void applyEffect(@Nonnull IShieldCapability shield, @Nonnull Player player, @Nullable DamageSource source, float damage, EffectTrigger trigger, int level) {

    }

    @Nonnull
    @Override
    public String getDescription() {
        return "";
    }
}
