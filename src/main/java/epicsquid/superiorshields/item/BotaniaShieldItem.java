package epicsquid.superiorshields.item;

import epicsquid.superiorshields.config.Config;
import epicsquid.superiorshields.shield.IShieldType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.api.mana.ManaItemHandler;

public class BotaniaShieldItem extends SuperiorShieldItem<IShieldType> {

	public BotaniaShieldItem(Properties props, IShieldType shieldType) {
		super(props.stacksTo(1), shieldType);
	}

	@Override
	public boolean useEnergyToRecharge(ItemStack stack, Player player) {
		return ManaItemHandler.instance().requestManaExact(stack, player, Config.SHIELD.MANA_CONSUMPTION.get(), true);
	}
}
