package epicsquid.superiorshields.shield.effect;

import epicsquid.superiorshields.lang.ModLang;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nonnull;
import java.util.List;

public class ShieldEffectFireNova extends ShieldEffectNova {

    private final int duration;

    public ShieldEffectFireNova(int duration, double radius) {
        super(radius, ModLang.FIRE_NOVA.getKey());
        this.duration = duration;
    }

    @Override
    protected void applyToEntities(@Nonnull List<LivingEntity> entities) {
        for (LivingEntity entity : entities) {
            entity.setSecondsOnFire(duration);
        }
    }
}
