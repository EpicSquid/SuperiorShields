package epicsquid.superiorshields.shield.perk;

import hellfirepvp.astralsorcery.common.constellation.perk.attribute.PerkAttributeModifier.Mode;
import hellfirepvp.astralsorcery.common.constellation.perk.tree.PerkTree;
import hellfirepvp.astralsorcery.common.event.APIRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static epicsquid.superiorshields.config.ConfigManager.astralSorcery;

public class PerkEventHandler {

  @SubscribeEvent
  public void onTypeRegister(APIRegistryEvent.PerkAttributeTypeRegister register) {
    AttributeTypeSuperiorShield attr = new AttributeTypeSuperiorShield();
    MinecraftForge.EVENT_BUS.register(attr);
    register.registerAttribute(attr);
  }

  @SubscribeEvent
  public void onPerkRegister(APIRegistryEvent.PerkRegister register) {

    if (!astralSorcery.enableAstralSorceryShieldPerks) {
      return;
    }

    ModifierPerkSuperiorShield addedShield1 = new ModifierPerkSuperiorShield("mod_superiorshields_added_shield_1", 27, 24);
    addedShield1.addModifier(astralSorcery.addShieldModPerk1, Mode.ADDITION, AttributeTypeSuperiorShield.ATTR_TYPE_SUPERIOR_SHIELD);

    ModifierPerkSuperiorShield addedShield2 = new ModifierPerkSuperiorShield("mod_superiorshields_added_shield_2", 28, 26);
    addedShield2.addModifier(astralSorcery.addShieldModPerk2, Mode.ADDITION, AttributeTypeSuperiorShield.ATTR_TYPE_SUPERIOR_SHIELD);

    ModifierPerkSuperiorShield addedShield3 = new ModifierPerkSuperiorShield("mod_superiorshields_added_shield_3", 30, 25);
    addedShield2.addModifier(astralSorcery.addShieldModPerk3, Mode.ADDITION, AttributeTypeSuperiorShield.ATTR_TYPE_SUPERIOR_SHIELD);

    KeyPerkSuperiorShield shieldPerk = new KeyPerkSuperiorShield("mod_superiorshields_key_shield", 32, 27);
    shieldPerk.addModifier(astralSorcery.addShieldKeyPerk, Mode.ADDITION, AttributeTypeSuperiorShield.ATTR_TYPE_SUPERIOR_SHIELD);

    register.registerPerk(addedShield1).connect(PerkTree.PERK_TREE.getAstralSorceryPerk("outer_s_inc_def_4"));
    register.registerPerk(addedShield2).connect(addedShield1);
    register.registerPerk(addedShield3).connect(addedShield2);
    register.registerPerk(shieldPerk).connect(addedShield3);
  }
}
