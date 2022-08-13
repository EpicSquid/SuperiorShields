package epicsquid.superiorshields.item;

import epicsquid.superiorshields.capability.shield.CapabilityRegistry;
import epicsquid.superiorshields.enchantment.ModEnchantments;
import epicsquid.superiorshields.event.ShieldEquippedEvent;
import epicsquid.superiorshields.lang.ModLang;
import epicsquid.superiorshields.shield.IShieldType;
import epicsquid.superiorshields.shield.ShieldHelper;
import epicsquid.superiorshields.shield.effect.EffectTrigger;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.List;

public class SuperiorShieldItem<T extends IShieldType> extends Item implements ISuperiorShield<T>, ICurioItem {
	private final T shieldType;

	// Used to ensure the potion effect is not applied every tick
	private int onTickEventTrigger = 1;
	private int rechargeCounter = 1;

	public SuperiorShieldItem(Item.Properties props, T shieldType) {
		super(props);
		this.shieldType = shieldType;
	}

	@Override
	public T getShield() {
		return shieldType;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return enchantment.category.equals(ModEnchantments.type);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(@Nonnull ItemStack stack, @Nullable Level worldIn, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);

		tooltip.add(ModLang.BLANK);
		tooltip.add(new TranslatableComponent(ModLang.EQUIP.getKey()).withStyle(ChatFormatting.GRAY));
		tooltip.add(new TranslatableComponent(ModLang.HP.getKey(), df.format(ShieldHelper.getShieldCapacity(stack))).withStyle(ChatFormatting.DARK_GREEN));
		tooltip.add(new TranslatableComponent(ModLang.RECHARGE_RATE.getKey(), df.format((float) shieldType.getRate() / 20f)).withStyle(ChatFormatting.DARK_GREEN));
		tooltip.add(new TranslatableComponent(ModLang.RECHARGE_DELAY.getKey(), df.format((float) shieldType.getDelay() / 20f)).withStyle(ChatFormatting.DARK_GREEN));
	}


	@Override
	public int getItemEnchantability(ItemStack stack) {
		return shieldType.getEnchantability();
	}


	@Override
	public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
		if (slotContext.entity() instanceof Player player && !ItemStack.isSameIgnoreDurability(prevStack, stack)) {
			CapabilityRegistry.getShield(player).ifPresent(shield -> {
				shield.setupShield(ShieldHelper.getShieldCapacity(stack), shield.getCurrentHp());
				MinecraftForge.EVENT_BUS.post(new ShieldEquippedEvent(player, shield));
				if (!player.level.isClientSide) {
					updateClient(player, shield);
				}
			});
		}
	}

	@Override
	public void onUnequip(SlotContext slotContext, ItemStack stack, ItemStack prevStack) {
		if (slotContext.entity() instanceof Player player && !ItemStack.isSameIgnoreDurability(stack, prevStack)) {
			CapabilityRegistry.getShield(player).ifPresent(shield -> {
				shield.invalidateShield();
				if (!player.level.isClientSide) {
					updateClient(player, shield);
				}
			});
		}
	}

	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
		if (slotContext.entity() instanceof Player player) {
			if (player.level.isClientSide) {
				return;
			}
			CapabilityRegistry.getShield(player).ifPresent(shield -> {
				var capacity = ShieldHelper.getShieldCapacity(stack);
				// Just make sure the max HP hasn't changed
				if (capacity != shield.getMaxHp()) {
					shield.setMaxHp(capacity);
				}
				if (shield.getTimeWithoutDamage() >= ShieldHelper.getShieldRechargeDelay(stack) && shield.getCurrentHp() < capacity) {
					if (shield.getTimeWithoutDamage() == ShieldHelper.getShieldRechargeDelay(stack) || rechargeCounter % ShieldHelper.getShieldRechargeRate(stack) == 0) {
						rechargeCounter = 1;
						rechargeShield(shield, stack, player);
						updateClient(player, shield);
						triggerShieldEffect(player, stack, null, 0f, EffectTrigger.RECHARGE);
						if (shield.getCurrentHp() >= capacity) {
							triggerShieldEffect(player, stack, null, 0f, EffectTrigger.FILLED);
						}
					} else {
						rechargeCounter++;
					}
				} else {
					shield.setTimeWithoutDamage(shield.getTimeWithoutDamage() + 1);
					if (shield.getCurrentHp() >= capacity) {
						if (onTickEventTrigger % 20 == 0) {
							onTickEventTrigger = 1;
							triggerShieldEffect(player, stack, null, 0f, EffectTrigger.FULL);
							updateClient(player, shield);
						} else {
							onTickEventTrigger++;
						}
					}
				}
			});
		}
	}

	@Override
	public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
		return true;
	}
}
