package epicsquid.superiorshields.event;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ShieldEquippedEvent extends PlayerEvent {

  private IShieldCapability shield;

  public ShieldEquippedEvent(@Nonnull EntityPlayer player, @Nonnull IShieldCapability shield) {
    super(player);
    this.shield = shield;
  }

  @Nonnull
  public IShieldCapability getShield() {
    return shield;
  }
}
