package epicsquid.superiorshields.item;

import epicsquid.superiorshields.capability.shield.CapabilityRegistry;
import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.enchantment.EnchantmentUtils;
import epicsquid.superiorshields.enchantment.ModEnchantments;
import epicsquid.superiorshields.event.ShieldEquippedEvent;
import epicsquid.superiorshields.lang.ModLang;
import epicsquid.superiorshields.network.NetworkHandler;
import epicsquid.superiorshields.network.PacketShieldUpdate;
import epicsquid.superiorshields.shield.IShieldType;
import epicsquid.superiorshields.shield.ShieldHelper;
import epicsquid.superiorshields.shield.effect.EffectTrigger;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.network.PacketDistributor;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.List;

public class SuperiorShieldItem<T extends IShieldType> extends Item implements SuperiorShield<T>, ICurioItem {
	private final T shieldType;

	// Used to ensure the potion effect is not applied every tick
	private int onTickEventTrigger = 0;
	private int rechargeCounter = 0;

	public SuperiorShieldItem(Item.Properties props, T shieldType) {
		super(props);
		this.shieldType = shieldType;
	}

	@Override
	public T getShield() {
		return shieldType;
	}

	@Override
	public float applyShield(Player player, ItemStack stack, float damage, DamageSource source) {
		var shieldOp = CapabilityRegistry.getShield(player).resolve();
		if (shieldOp.isPresent()) {
			IShieldCapability shield = shieldOp.get();
			if (shield.getCurrentHp() > 0) {
				triggerShieldEffect(player, stack, source, damage, EffectTrigger.DAMAGE);
				if (damage > shield.getCurrentHp()) {
					triggerShieldEffect(player, stack, source, damage, EffectTrigger.EMPTY);
				}
			}
			return absorbDamage(player, shield, shield.getCurrentHp() - damage);
		}
		return damage;
	}

	protected float absorbDamage(Player player, IShieldCapability shield, float absorbed) {
		shield.setCurrentHp(absorbed);
		resetShieldDelay(shield);
		updateClient(player, shield);
		return absorbed < 0f ? -1f * absorbed : 0f;
	}

	@Override
	public void rechargeShield(IShieldCapability shield, ItemStack stack, Player player) {
		if (shield.getCurrentHp() < shield.getMaxHp()) {
			if (useEnergyToRecharge(stack, player)) {
				shield.setCurrentHp(shield.getCurrentHp() + 1.0f);
			}
		}
	}

	@Override
	public void resetShieldDelay(IShieldCapability shield) {
		shield.setTimeWithoutDamage(0);
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
		tooltip.add(new TranslatableComponent(ModLang.RECHARGE_RATE.getKey(), df.format((float) shieldType.getShieldRechargeRate() / 20f)).withStyle(ChatFormatting.DARK_GREEN));
		tooltip.add(new TranslatableComponent(ModLang.RECHARGE_DELAY.getKey(), df.format((float) shieldType.getShieldRechargeDelay() / 20f)).withStyle(ChatFormatting.DARK_GREEN));
	}

	protected void updateClient(Player player, IShieldCapability shield) {
		NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new PacketShieldUpdate(shield.getCurrentHp(), shield.getMaxHp()));
	}

	public void triggerShieldEffect(Player player, ItemStack stack, @Nullable DamageSource source, float damage, EffectTrigger trigger) {
		var shieldOp = CapabilityRegistry.getShield(player).resolve();
		if (shieldOp.isPresent()) {
			IShieldCapability shield = shieldOp.get();
			// Always apply at level 1 when the effect is on the shield, not on the enchantment
			shieldType.getEffect().applyEffect(shield, player, source, damage, trigger, 1);
			EnchantmentUtils.triggerEnchantmentEffect(shield, player, stack, source, damage, trigger);
		}
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return enchantment.category.equals(ModEnchantments.type) || enchantment.equals(Enchantments.UNBREAKING) || enchantment.equals(Enchantments.MENDING);
	}


	@Override
	public int getItemEnchantability(ItemStack stack) {
		return shieldType.getEnchantability();
	}


	@Override
	public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
		if (slotContext.entity() instanceof Player player && !player.level.isClientSide && !ItemStack.isSameIgnoreDurability(prevStack, stack)) {
			CapabilityRegistry.getShield(player).ifPresent(shield -> {
				float capacity = ShieldHelper.getShieldCapacity(stack);

				shield.setMaxHp(capacity);
				if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.JUMP_START.get(), stack) > 0) {
					shield.setCurrentHp(capacity);
					useEnergyToRecharge(stack, player);
				} else {
					shield.setCurrentHp(0);
				}
				shield.setTimeWithoutDamage(0);
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
				shield.setMaxHp(0f);
				shield.setCurrentHp(0f);
				shield.setTimeWithoutDamage(0);
				updateClient(player, shield);
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
				if (shield.getTimeWithoutDamage() >= ShieldHelper.getShieldRechargeDelay(stack) && shield.getCurrentHp() < shield.getMaxHp()) {
					if (rechargeCounter % ShieldHelper.getShieldRechargeRate(stack) == 0) {
						rechargeShield(shield, stack, player);
						updateClient(player, shield);
						triggerShieldEffect(player, stack, null, 0f, EffectTrigger.RECHARGE);
						if (shield.getCurrentHp() >= shield.getMaxHp()) {
							triggerShieldEffect(player, stack, null, 0f, EffectTrigger.FILLED);
						}
					} else {
						rechargeCounter++;
					}
				} else {
					shield.setTimeWithoutDamage(shield.getTimeWithoutDamage() + 1);
					if (shield.getCurrentHp() >= shield.getMaxHp()) {
						if (onTickEventTrigger >= 20) {
							onTickEventTrigger = 0;
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
