package epicsquid.superiorshields.network;

import epicsquid.superiorshields.capability.IShieldCapability;
import epicsquid.superiorshields.capability.SuperiorShieldsCapabilityManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketShieldUpdate implements IMessage {

  public PacketShieldUpdate() {

  }

  private float currentHp;
  private float maxHp;
  public PacketShieldUpdate(float currentHp, float maxHp) {
    this.currentHp = currentHp;
    this.maxHp = maxHp;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    currentHp = buf.readFloat();
    maxHp = buf.readFloat();
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeFloat(currentHp);
    buf.writeFloat(maxHp);
  }

  public static class Handler implements IMessageHandler<PacketShieldUpdate, IMessage> {

    @SideOnly(Side.CLIENT)
    @Override
    public IMessage onMessage(PacketShieldUpdate message, MessageContext ctx) {
      EntityPlayerSP player = Minecraft.getMinecraft().player;
      if (player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null)) {
        IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
        shield.setMaxHp(message.maxHp);
        shield.setCurrentHp(message.currentHp);
      }
      return null;
    }
  }
}
