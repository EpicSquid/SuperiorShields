package epicsquid.superiorshields.shield.effect;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;
import java.util.logging.Level;

public class ShieldEffectSpawn<E extends Entity> implements IShieldEffect {

    private Random random = new Random();
    private float chanceToSpawn;
    private Class<E> entityClass;
    private String description;

    public ShieldEffectSpawn(@Nonnull Class<E> entityClass, float chanceToSpawn, String description) {
        this.entityClass = entityClass;
        this.chanceToSpawn = chanceToSpawn;
        this.description = description;
    }

    @Override
    public void applyEffect(@Nonnull IShieldCapability shield, @Nonnull Player player, @Nullable DamageSource source, float damage, EffectTrigger trigger, int level) {
        if (trigger == EffectTrigger.DAMAGE && !player.level.isClientSide && random.nextFloat() < chanceToSpawn * level) {
            try {
                Entity entity = entityClass.getConstructor(Level.class).newInstance(player.level);
                entity.setPos(player.position().x + random.nextDouble(), player.position().y + player.getEyeHeight(), player.position().z + random.nextDouble());
                player.level.addFreshEntity(entity);
            } catch (Exception e) {
                // TODO proper exception logging
                System.out.println("Error: Could not create entity.");
            }

        }
    }

    @Nonnull
    @Override
    public String getDescription() {
        return I18n.get(description);
    }
}
