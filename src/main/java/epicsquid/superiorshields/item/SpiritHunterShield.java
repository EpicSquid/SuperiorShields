//import com.google.common.collect.Multimap;
//import com.sammy.malum.core.setup.content.AttributeRegistry;
//import epicsquid.superiorshields.shield.IShieldType;
//import net.minecraft.world.entity.ai.attributes.Attribute;
//import net.minecraft.world.entity.ai.attributes.AttributeModifier;
//import net.minecraft.world.item.ItemStack;
//import team.lodestar.lodestone.setup.LodestoneAttributeRegistry;
//import top.theillusivec4.curios.api.SlotContext;
//
//import java.util.UUID;
//
//public class SpiritHunterShield extends VanillaShieldItem {
//
//
//	public SpiritHunterShield(Properties props, IShieldType shieldType) {
//		super(props, shieldType);
//	}
//
//	@Override
//	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
//		var attributes = super.getAttributeModifiers(slotContext, uuid, stack);
//
//		attributes.put(LodestoneAttributeRegistry.MAGIC_PROFICIENCY.get(), new AttributeModifier(uuid, "Magic Proficiency", 1f, AttributeModifier.Operation.ADDITION));
//		attributes.put(AttributeRegistry.SCYTHE_PROFICIENCY.get(), new AttributeModifier(uuid, "Scythe Proficiency", 1f, AttributeModifier.Operation.ADDITION));
//		return attributes;
//	}
//}
