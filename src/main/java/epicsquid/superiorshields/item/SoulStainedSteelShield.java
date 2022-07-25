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

public class SoulStainedSteelShield extends VanillaShieldItem {


	public SoulStainedSteelShield(Properties props, IShieldType shieldType) {
		super(props, shieldType);
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
		var attributes = super.getAttributeModifiers(slotContext, uuid, stack);

		attributes.put(OrtusAttributes.MAGIC_RESISTANCE.get(), new AttributeModifier(UUID.randomUUID(), "Magic Resistance", 1f, AttributeModifier.Operation.ADDITION));
		attributes.put(AttributeRegistry.SOUL_WARD_CAP.get(), new AttributeModifier(UUID.randomUUID(), "Soul Ward Cap", 3f, AttributeModifier.Operation.ADDITION));
		return attributes;
	}
}
