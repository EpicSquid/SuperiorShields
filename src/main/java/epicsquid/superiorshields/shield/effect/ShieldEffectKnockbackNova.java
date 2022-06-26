package epicsquid.superiorshields.shield.effect;

import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nonnull;
import java.util.List;

public class ShieldEffectKnockbackNova extends ShieldEffectNova {

    private float strength;
    private float x;
    private float z;

    public ShieldEffectKnockbackNova(double radius, float strength, float x, float z) {
        super(radius, "shield.effect.nova.knockback");
        this.strength = strength;
        this.x = x;
        this.z = z;
    }

    @Override
    protected void applyToEntities(@Nonnull List<LivingEntity> entities) {
        for (LivingEntity entity : entities) {
            // This is supposed to be the knockback, so hopefully it is
            entity.knockback(strength, x, z);
        }
    }
}
