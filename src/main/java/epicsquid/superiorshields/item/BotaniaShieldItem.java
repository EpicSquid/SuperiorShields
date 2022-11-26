package epicsquid.superiorshields.item;

import epicsquid.superiorshields.config.Config;
import epicsquid.superiorshields.shield.IShieldType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import vazkii.botania.api.mana.ManaItemHandler;

public class BotaniaShieldItem extends VanillaShieldItem {

	public BotaniaShieldItem(Properties props, IShieldType shieldType) {
		super(props, shieldType);
	}

	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
		super.curioTick(slotContext, stack);
		if ((slotContext.entity() instanceof Player player) && stack.isDamaged()
				&& ManaItemHandler.instance().requestManaExact(stack, player, Config.SHIELD.MANA_CONSUMPTION.get(), true)) {
			stack.setDamageValue(stack.getDamageValue() - 1);
		}
	}
}
