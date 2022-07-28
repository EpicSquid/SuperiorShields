package epicsquid.superiorshields.item;

import com.google.common.collect.Multimap;
import com.sammy.malum.core.setup.content.AttributeRegistry;
import com.sammy.ortus.setup.OrtusAttributes;
import epicsquid.superiorshields.shield.IShieldType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class SpiritHunterShield extends VanillaShieldItem {


	public SpiritHunterShield(Properties props, IShieldType shieldType) {
		super(props, shieldType);
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
		var attributes = super.getAttributeModifiers(slotContext, uuid, stack);

		attributes.put(OrtusAttributes.MAGIC_PROFICIENCY.get(), new AttributeModifier(UUID.fromString("a6860341-acc9-458f-bf3a-23a78df40b95"), "Magic Proficiency", 1f, AttributeModifier.Operation.ADDITION));
		attributes.put(AttributeRegistry.SCYTHE_PROFICIENCY.get(), new AttributeModifier(UUID.fromString("73662800-03c3-487a-8a58-1d4868c8d1ce"), "Scythe Proficiency", 1f, AttributeModifier.Operation.ADDITION));
		return attributes;
	}
}
