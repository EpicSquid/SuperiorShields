package epicsquid.superiorshields.shield.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShieldEffectPotionNova extends ShieldEffectNova {

    private List<MobEffect> effects;
    private int duration;

    public ShieldEffectPotionNova(@Nonnull MobEffect effect, int duration, double radius, String description) {
        super(radius, description);
        this.effects = new ArrayList<>();
        effects.add(effect);
        this.duration = duration;
    }

    public ShieldEffectPotionNova(int duration, double radius, String description, @Nonnull MobEffect... effects) {
        super(radius, description);
        this.effects = Arrays.asList(effects);
        this.duration = duration;
    }

    @Override
    protected void applyToEntities(@Nonnull List<LivingEntity> entities) {
        for (LivingEntity entity : entities) {
            for (MobEffect effect : effects) {
                entity.addEffect(new MobEffectInstance(effect, duration));
            }
        }
    }
}
