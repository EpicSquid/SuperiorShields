package epicsquid.superiorshields.item;

import epicsquid.superiorshields.RegistryManager;
import epicsquid.superiorshields.capability.CuriosItemCapabilityProvider;
import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.capability.shield.SuperiorShieldsCapabilityManager;
import epicsquid.superiorshields.enchantment.ModEnchantments;
import epicsquid.superiorshields.event.ShieldEquippedEvent;
import epicsquid.superiorshields.network.NetworkHandler;
import epicsquid.superiorshields.network.SPacketShieldUpdate;
import epicsquid.superiorshields.shield.ShieldHelper;
import epicsquid.superiorshields.shield.ShieldType;
import epicsquid.superiorshields.shield.effect.EffectTrigger;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.network.NetworkDirection;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.List;

public class SuperiorShieldItem<T extends ShieldType> extends Item implements SuperiorShield<T>, ICurio {

	private int ticksSinceLastRecharge = 0;
	private T shieldType;

	// Used to ensure the potion effect is not applied every tick
	private int onTickEventTrigger = 0;

	public SuperiorShieldItem(Properties props, T shieldType) {
		super(props);
		this.shieldType = shieldType;
	}

	@Override
	public T getShield() {
		return shieldType;
	}

	@Override
	public float applyShield(PlayerEntity player, ItemStack stack, float damage, DamageSource source) {
		if (player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).isPresent()) {
			IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).orElseGet(() -> null);
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

	protected float absorbDamage(PlayerEntity player, IShieldCapability shield, float absorbed) {
		shield.setCurrentHp(absorbed);
		resetShieldDelay(shield);
		updateClient(player, shield);
		return absorbed < 0f ? -1f * absorbed : 0f;
	}

	@Override
	public void rechargeShield(IShieldCapability shield, ItemStack stack, PlayerEntity player) {
		if (shield.getCurrentHp() < shield.getMaxHp()) {
			if (useEnergyToRecharge(stack, player)) {
				shield.setCurrentHp(shield.getCurrentHp() + 1.0f);
			}
		}
	}

	/**
	 * Triggers the use of energy to recharge.
	 *
	 * @param stack The stack to get the capability to recharge from.
	 * @return true if there was enough energy to recharge.
	 */
	protected boolean useEnergyToRecharge(ItemStack stack, PlayerEntity player) {
		return true;
	}

	@Override
	public void resetShieldDelay(IShieldCapability shield) {
		shield.setTimeWithoutDamage(0);
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
		return new CuriosItemCapabilityProvider(new ICurio() {

			@Override
			public void curioTick(String identifier, int index, LivingEntity livingEntity) {
				if (livingEntity instanceof PlayerEntity) {
					PlayerEntity player = (PlayerEntity) livingEntity;
					if (player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).isPresent()) {
						if (player.getCommandSenderWorld().isClientSide) {
							return;
						}
						IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).orElseGet(() -> null);
						if (shield.getTimeWithoutDamage() >= ShieldHelper.getShieldRechargeRate(stack) && shield.getCurrentHp() < shield.getMaxHp()) {
							if (ticksSinceLastRecharge < ShieldHelper.getShieldRechargeRate(stack)) {
								ticksSinceLastRecharge++;
							} else {
								ticksSinceLastRecharge = 0;
								rechargeShield(shield, stack, player);
								updateClient(player, shield);
								triggerShieldEffect(player, stack, null, 0f, EffectTrigger.RECHARGE);
								if (shield.getCurrentHp() >= shield.getMaxHp()) {
									triggerShieldEffect(player, stack, null, 0f, EffectTrigger.FILLED);
								}
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
					}
				}
			}

			@Override
			public boolean canRightClickEquip() {
				return true;
			}
		});
	}



	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);

		tooltip.add(new TranslationTextComponent("superiorshields.tooltip.blank"));
		tooltip.add(new TranslationTextComponent("superiorshields.tooltip.equip").withStyle(TextFormatting.GRAY));
		tooltip.add(new TranslationTextComponent("superiorshields.tooltip.hp", df.format(ShieldHelper.getShieldCapacity(stack))).withStyle(TextFormatting.DARK_GREEN));
		tooltip.add(new TranslationTextComponent("superiorshields.tooltip.recharge_rate", df.format((float) shieldType.getShieldRechargeRate() / 20f)).withStyle(TextFormatting.DARK_GREEN));
		tooltip.add(new TranslationTextComponent("superiorshields.tooltip.recharge_delay", df.format((float) shieldType.getShieldRechargeDelay() / 20f)).withStyle(TextFormatting.DARK_GREEN));
	}

	protected void updateClient(PlayerEntity player, IShieldCapability shield) {
		if (player instanceof ServerPlayerEntity) {
			NetworkHandler.INSTANCE.sendTo(new SPacketShieldUpdate(shield.getCurrentHp(), shield.getMaxHp()), ((ServerPlayerEntity) player).connection.connection, NetworkDirection.PLAY_TO_CLIENT);
		}
	}

	public void triggerShieldEffect(PlayerEntity player, ItemStack stack, @Nullable DamageSource source, float damage, EffectTrigger trigger) {
		if (player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).isPresent()) {
			IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).orElseGet(() -> null);
			shieldType.getEffect().applyEffect(shield, player, source, damage, trigger);
		}
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return enchantment.category.equals(RegistryManager.type);
	}


	@Override
	public int getItemEnchantability(ItemStack stack) {
		return shieldType.getEnchantability();
	}

	@Override
	public void equip(PlayerEntity player, ItemStack stack) {
		if (player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).isPresent() && !player.getCommandSenderWorld().isClientSide) {
			IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).orElseGet(() -> null);

			float capacity = ShieldHelper.getShieldCapacity(stack);

			shield.setMaxHp(capacity);
			if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.JUMP_START, stack) > 0) {
				shield.setCurrentHp(capacity);
				useEnergyToRecharge(stack, player);
			} else {
				shield.setCurrentHp(0);
			}
			shield.setTimeWithoutDamage(0);
			MinecraftForge.EVENT_BUS.post(new ShieldEquippedEvent(player, shield));
			if (!player.getCommandSenderWorld().isClientSide) {
				updateClient(player, shield);
			}
		}
	}

	@Override
	public void unequip(PlayerEntity player) {
		if (player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).isPresent() && !player.getCommandSenderWorld().isClientSide) {
			IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).orElseGet(() -> null);
			shield.setMaxHp(0f);
			shield.setCurrentHp(0f);
			shield.setTimeWithoutDamage(0);
			updateClient(player, shield);
		}
	}
}
