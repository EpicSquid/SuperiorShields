package epicsquid.superiorshields.event;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;

import javax.annotation.Nonnull;

public class ShieldEquippedEvent extends PlayerEvent {

    private IShieldCapability shield;

    public ShieldEquippedEvent(@Nonnull Player player, @Nonnull IShieldCapability shield) {
        super(player);
        this.shield = shield;
    }

    @Nonnull
    public IShieldCapability getShield() {
        return shield;
    }
}
