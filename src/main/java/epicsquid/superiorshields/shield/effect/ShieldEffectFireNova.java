package epicsquid.superiorshields.shield.effect;

import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nonnull;
import java.util.List;

public class ShieldEffectFireNova extends ShieldEffectNova {

    private int duration;

    public ShieldEffectFireNova(int duration, double radius) {
        super(radius, "superior_shield.effect.fire_nova");
        this.duration = duration;
    }

    @Override
    protected void applyToEntities(@Nonnull List<LivingEntity> entities) {
        for (LivingEntity entity : entities) {
            entity.setSecondsOnFire(duration);
        }
    }
}
