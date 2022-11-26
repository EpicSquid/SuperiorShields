package epicsquid.superiorshields.item;

import com.hollingsworth.arsnouveau.common.capability.CapabilityRegistry;
import com.hollingsworth.arsnouveau.common.enchantment.EnchantmentRegistry;
import com.hollingsworth.arsnouveau.common.spell.casters.ReactiveCaster;
import epicsquid.superiorshields.shield.IShieldType;
import epicsquid.superiorshields.shield.effect.EffectTrigger;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import top.theillusivec4.curios.api.SlotContext;

public class ArsShieldItem extends VanillaShieldItem {

	public ArsShieldItem(Properties props, IShieldType shieldType) {
		super(props, shieldType);
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

	public static void triggerReactive(ItemStack shield, Player player, EffectTrigger trigger) {
		if (trigger == EffectTrigger.EMPTY
				&& EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.REACTIVE_ENCHANTMENT, shield) * .25 >= Math.random()
				&& new ReactiveCaster(shield).getSpell().isValid()) {
			ReactiveCaster reactiveCaster = new ReactiveCaster(shield);
			reactiveCaster.castSpell(player.getCommandSenderWorld(), player, InteractionHand.MAIN_HAND, null);
		}
	}
}
