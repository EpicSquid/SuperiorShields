package epicsquid.superiorshields.shield.perk;

import javax.annotation.Nonnull;

import epicsquid.superiorshields.SuperiorShields;
import epicsquid.superiorshields.event.ShieldEquippedEvent;
import epicsquid.superiorshields.network.PacketHandler;
import epicsquid.superiorshields.network.PacketShieldUpdate;
import hellfirepvp.astralsorcery.AstralSorcery;
import hellfirepvp.astralsorcery.common.constellation.perk.PerkAttributeHelper;
import hellfirepvp.astralsorcery.common.constellation.perk.attribute.PerkAttributeType;
import hellfirepvp.astralsorcery.common.data.research.ResearchManager;
import hellfirepvp.astralsorcery.common.event.AttributeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

public class AttributeTypeSuperiorShield extends PerkAttributeType {

  public static final String ATTR_TYPE_SUPERIOR_SHIELD = SuperiorShields.MODID + "." + AstralSorcery.MODID + ".superiorshield";

  public AttributeTypeSuperiorShield() {
    super(ATTR_TYPE_SUPERIOR_SHIELD);
  }

  @SubscribeEvent
  public void onShieldEquiped(ShieldEquippedEvent event) {
    EntityPlayer player = event.getEntityPlayer();
    if (player.world.isRemote) {
      return;
    }

    float newMaxHp = PerkAttributeHelper.getOrCreateMap(player, Side.SERVER)
        .modifyValue(player, ResearchManager.getProgress(player, Side.SERVER), AttributeTypeSuperiorShield.ATTR_TYPE_SUPERIOR_SHIELD,
            event.getShield().getMaxHp());
    newMaxHp = AttributeEvent.postProcessModded(player, this, newMaxHp);
    event.getShield().setMaxHp(newMaxHp);

    PacketHandler.INSTANCE.sendTo(new PacketShieldUpdate(event.getShield().getCurrentHp(), event.getShield().getMaxHp()), (EntityPlayerMP) player);
  }
}
