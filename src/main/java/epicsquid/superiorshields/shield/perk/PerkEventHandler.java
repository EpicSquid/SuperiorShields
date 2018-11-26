package epicsquid.superiorshields.shield.perk;

import epicsquid.superiorshields.SuperiorShields;
import hellfirepvp.astralsorcery.common.constellation.perk.attribute.PerkAttributeModifier.Mode;
import hellfirepvp.astralsorcery.common.constellation.perk.tree.PerkTree;
import hellfirepvp.astralsorcery.common.event.APIRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = SuperiorShields.MODID)
public class PerkEventHandler {

  @SubscribeEvent
  public static void onTypeRegister(APIRegistryEvent.PerkAttributeTypeRegister register) {
    AttributeTypeSuperiorShield attr = new AttributeTypeSuperiorShield();
    MinecraftForge.EVENT_BUS.register(attr);
    register.registerAttribute(attr);
  }

  @SubscribeEvent
  public static void onPerkRegister(APIRegistryEvent.PerkRegister register) {
    KeyPerkSuperiorShield shieldPerk = new KeyPerkSuperiorShield("mod_superiorshields_key_shield", 28, 21);
    shieldPerk.addModifier(2f, Mode.ADDITION, AttributeTypeSuperiorShield.ATTR_TYPE_SUPERIOR_SHIELD);

    ModifierPerkSuperiorShield addedShield = new ModifierPerkSuperiorShield("mod_superiorshields_added_shield", 26, 22);
    addedShield.addModifier(2f, Mode.ADDITION, AttributeTypeSuperiorShield.ATTR_TYPE_SUPERIOR_SHIELD);

    register.registerPerk(addedShield).connect(PerkTree.PERK_TREE.getAstralSorceryPerk("outer_s_inc_def_4"));
    register.registerPerk(shieldPerk).connect(addedShield);
  }
}
