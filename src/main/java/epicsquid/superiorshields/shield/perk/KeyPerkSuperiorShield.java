package epicsquid.superiorshields.shield.perk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.superiorshields.SuperiorShields;
import hellfirepvp.astralsorcery.common.constellation.perk.tree.nodes.KeyPerk;
import hellfirepvp.astralsorcery.common.event.AttributeEvent;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(modid = SuperiorShields.MODID)
public class KeyPerkSuperiorShield extends KeyPerk {

  public KeyPerkSuperiorShield(@Nonnull String name, int x, int y) {
    super(name, x, y);
  }

  @Nullable
  @Override
  @SideOnly(Side.CLIENT)
  public Collection<String> getSource() {
    List<String> source = new ArrayList<>();
    source.add(I18n.format("perk.superiorshields.shield"));
    return source;
  }

  @SubscribeEvent
  public static void on(AttributeEvent.PostProcessModded event) {
  }
}
