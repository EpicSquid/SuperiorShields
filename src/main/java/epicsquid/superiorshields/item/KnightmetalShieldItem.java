package epicsquid.superiorshields.item;

import com.google.common.collect.Multimap;
import epicsquid.superiorshields.shield.IShieldType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class KnightmetalShieldItem extends VanillaShieldItem {

	public KnightmetalShieldItem(Properties props, IShieldType shieldType) {
		super(props, shieldType);
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
		var attributes = super.getAttributeModifiers(slotContext, uuid, stack);

		attributes.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("d295b350-21c6-45b6-ad2c-2b6dbbf80b0a"), "Curio armor boost", 4, AttributeModifier.Operation.ADDITION));
		return attributes;
	}
}
