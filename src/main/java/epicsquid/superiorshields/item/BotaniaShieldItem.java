package epicsquid.superiorshields.item;

import epicsquid.superiorshields.config.Config;
import epicsquid.superiorshields.shield.BotaniaShield;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.api.mana.ManaItemHandler;

public class BotaniaShieldItem extends SuperiorShieldItem<BotaniaShield> {

	public BotaniaShieldItem(Properties props, BotaniaShield shieldType) {
		super(props.stacksTo(1), shieldType);
	}

	@Override
	public boolean useEnergyToRecharge(ItemStack stack, Player player) {
		return ManaItemHandler.instance().requestManaExact(stack, player, Config.SHIELD.MANA_CONSUMPTION.get(), true);
	}
}
