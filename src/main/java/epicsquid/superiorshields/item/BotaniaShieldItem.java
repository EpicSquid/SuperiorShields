package epicsquid.superiorshields.item;

import epicsquid.superiorshields.shield.ShieldType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.api.mana.ManaItemHandler;

public class BotaniaShieldItem extends SuperiorShieldItem<ShieldType>{

	private int manaToConsume = 400;

	public BotaniaShieldItem(Properties props, ShieldType shieldType) {
		super(props, shieldType);
	}

	@Override
	public boolean useEnergyToRecharge(ItemStack stack, Player player) {
		return ManaItemHandler.instance().requestManaExact(stack, player, manaToConsume, true);
	}
}
