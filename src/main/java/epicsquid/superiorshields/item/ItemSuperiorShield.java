package epicsquid.superiorshields.item;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Nullable;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.capability.shield.SuperiorShieldsCapabilityManager;
import epicsquid.superiorshields.event.ShieldEquippedEvent;
import epicsquid.superiorshields.network.SPacketShieldUpdate;
import epicsquid.superiorshields.network.NetworkHandler;
import epicsquid.superiorshields.shield.IShieldType;
import epicsquid.superiorshields.shield.effect.EffectTrigger;
import net.minecraft.client.util.ITooltipFlag;
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
import net.minecraftforge.fml.network.PacketDistributor;
import top.theillusivec4.curios.api.capability.ICurio;
import top.theillusivec4.curios.common.capability.CapCurioItem;

public class ItemSuperiorShield<T extends IShieldType> extends Item implements ISuperiorShield, ICurio {

	private int ticksSinceLastRecharge = 0;
	protected T shieldType;

	// Used to ensure the potion effect is not applied every tick
	private int onTickEventTrigger = 0;

	public ItemSuperiorShield(Properties props, T shieldType) {
		super(props);
		this.shieldType = shieldType;
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
		return CapCurioItem.createProvider(new ICurio() {


			@Override
			public void onCurioTick(String identifier, LivingEntity livingEntity) {
				if (livingEntity instanceof PlayerEntity) {
					PlayerEntity player = (PlayerEntity) livingEntity;
					if (player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).isPresent()) {
						if (player.world.isRemote) {
							return;
						}
						IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).orElseGet(() -> null);
						if (shield.getTimeWithoutDamage() >= shieldType.getShieldRechargeDelay() && shield.getCurrentHp() < shield.getMaxHp()) {
							if (ticksSinceLastRecharge < shieldType.getShieldRechargeRate()) {
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
			public void onEquipped(String identifier, LivingEntity livingEntity) {
				if (livingEntity instanceof PlayerEntity) {
					PlayerEntity player = (PlayerEntity) livingEntity;
					if (player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).isPresent() && !player.world.isRemote) {
						IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).orElseGet(() -> null);
						shield.setMaxHp(shieldType.getMaxShieldHp());
						shield.setCurrentHp(0);
						shield.setTimeWithoutDamage(0);
						MinecraftForge.EVENT_BUS.post(new ShieldEquippedEvent(player, shield));
						if (!player.world.isRemote) {
							updateClient(player, shield);
						}
					}
				}
			}

			@Override
			public void onUnequipped(String identifier, LivingEntity livingEntity) {
				if (livingEntity instanceof PlayerEntity) {
					PlayerEntity player = (PlayerEntity) livingEntity;
					if (player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).isPresent() && !player.world.isRemote) {
						IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).orElseGet(() -> null);
						shield.setMaxHp(0f);
						shield.setCurrentHp(0f);
						shield.setTimeWithoutDamage(0);
						updateClient(player, shield);
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
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		tooltip.add(new TranslationTextComponent("superiorshields.tooltip.blank"));
		tooltip.add(new TranslationTextComponent("superiorshields.tooltip.equip").applyTextStyle(TextFormatting.GRAY));
		tooltip.add(new TranslationTextComponent("superiorshields.tooltip.hp", df.format(shieldType.getMaxShieldHp())).applyTextStyle(TextFormatting.DARK_GREEN));
		tooltip.add(new TranslationTextComponent("superiorshields.tooltip.recharge_rate", df.format((float) shieldType.getShieldRechargeRate() / 20f)).applyTextStyle(TextFormatting.DARK_GREEN));
		tooltip.add(new TranslationTextComponent("superiorshields.tooltip.recharge_delay", df.format((float) shieldType.getShieldRechargeDelay() / 20f)).applyTextStyle(TextFormatting.DARK_GREEN));
		//		tooltip.add(I18n.format("superiorshields.tooltip.hp") + " " + shieldType.getMaxShieldHp() + " " + I18n.format("superiorshields.tooltip.hpDetail"));
		//		tooltip.add(I18n.format("superiorshields.tooltip.rechargeDelay") + " " + (float) shieldType.getShieldRechargeDelay() / 20 + " " + I18n
		//				.format("superiorshields.tooltip.rechargeDelayTime"));
		//		tooltip.add(I18n.format("superiorshields.tooltip.rechargeRate") + " " + 1f / ((float) shieldType.getShieldRechargeRate() / 20) + " " + I18n
		//				.format("superiorshields.tooltip.rechargeRateTime"));
		//
		//		if (!(shieldType.getEffect() instanceof ShieldEffectNone)) {
		//			tooltip.add(shieldType.getEffect().getDescription());
		//		}
	}

	protected void updateClient(PlayerEntity player, IShieldCapability shield) {
		if (player instanceof ServerPlayerEntity) {
			NetworkHandler.INSTANCE.sendTo(new SPacketShieldUpdate(shield.getCurrentHp(), shield.getMaxHp()), ((ServerPlayerEntity) player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
	}

	public void triggerShieldEffect(PlayerEntity player, ItemStack stack, @Nullable DamageSource source, float damage, EffectTrigger trigger) {
		if (player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).isPresent()) {
			IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).orElseGet(() -> null);
			shieldType.getEffect().applyEffect(shield, player, source, damage, trigger);
		}
	}
}
