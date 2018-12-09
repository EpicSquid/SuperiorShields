package epicsquid.superiorshields.shield.perk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import hellfirepvp.astralsorcery.common.constellation.perk.tree.nodes.KeyPerk;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

  @Override
  public boolean addLocalizedTooltip(Collection<String> tooltip) {
    super.addLocalizedTooltip(tooltip);
    return false;
  }
}
