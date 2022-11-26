package epicsquid.superiorshields.item;

import com.google.common.collect.Multimap;
import epicsquid.superiorshields.shield.IShieldType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import vazkii.botania.common.handler.PixieHandler;

import java.util.UUID;

public class ElementiumShieldItem extends BotaniaShieldItem {

	public ElementiumShieldItem(Properties props, IShieldType shieldType) {
		super(props, shieldType);
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
		var attributes = super.getAttributeModifiers(slotContext, uuid, stack);

		attributes.put(PixieHandler.PIXIE_SPAWN_CHANCE, new AttributeModifier(uuid, "Pixie Spawn Chance Boost", 0.25, AttributeModifier.Operation.ADDITION));
		return attributes;
	}
}
