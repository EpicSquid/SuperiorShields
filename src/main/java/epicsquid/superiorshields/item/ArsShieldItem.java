package epicsquid.superiorshields.item;

import com.hollingsworth.arsnouveau.common.capability.CapabilityRegistry;
import epicsquid.superiorshields.shield.IShieldType;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class ArsShieldItem extends VanillaShieldItem {

	public ArsShieldItem(Properties props, IShieldType shieldType) {
		super(props.stacksTo(1), shieldType);
	}

	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
		super.curioTick(slotContext, stack);
		CapabilityRegistry.getMana(slotContext.entity()).ifPresent(mana -> {
			if (mana.getCurrentMana() > 20 && stack.isDamaged()) {
				mana.removeMana(20);
				stack.setDamageValue(stack.getDamageValue() - 1);
			}
		});
	}
}
