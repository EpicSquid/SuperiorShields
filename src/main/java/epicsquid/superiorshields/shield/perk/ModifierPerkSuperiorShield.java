package epicsquid.superiorshields.shield.perk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import hellfirepvp.astralsorcery.common.constellation.perk.attribute.AttributeModifierPerk;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModifierPerkSuperiorShield extends AttributeModifierPerk {

  public ModifierPerkSuperiorShield(String name, int x, int y) {
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
}
