package epicsquid.superiorshields.item;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.capability.shield.SuperiorShieldsCapabilityManager;
import epicsquid.superiorshields.event.ShieldEquippedEvent;
import epicsquid.superiorshields.network.PacketHandler;
import epicsquid.superiorshields.network.PacketShieldUpdate;
import epicsquid.superiorshields.shield.IShieldType;
import epicsquid.superiorshields.shield.effect.EffectTrigger;
import epicsquid.superiorshields.shield.effect.ShieldEffectNone;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;

public class ItemSuperiorShield<T extends IShieldType> extends Item implements ISuperiorShield {

	private int ticksSinceLastRecharge = 0;
	protected T shieldType;

	// Used to ensure the potion effect is not applied every tick
	private int onTickEventTrigger = 0;

	public ItemSuperiorShield(Properties props, T shieldType) {
		super(props);
		this.shieldType = shieldType;
	}

	@Override
	public float applyShield(@Nonnull PlayerEntity player, @Nonnull ItemStack stack, float damage, @Nonnull DamageSource source) {
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

	protected float absorbDamage(@Nonnull PlayerEntity player, @Nonnull IShieldCapability shield, float absorbed) {
		shield.setCurrentHp(absorbed);
		resetShieldDelay(shield);
		updateClient(player, shield);
		return absorbed < 0f ? -1f * absorbed : 0f;
	}

	@Override
	public void rechargeShield(@Nonnull IShieldCapability shield, @Nonnull ItemStack stack, @Nonnull PlayerEntity player) {
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
	protected boolean useEnergyToRecharge(@Nonnull ItemStack stack, @Nonnull PlayerEntity player) {
		return true;
	}

	@Override
	public void resetShieldDelay(@Nonnull IShieldCapability shield) {
		shield.setTimeWithoutDamage(0);
	}

	@Override
	public void onWornTick(@Nonnull ItemStack stack, @Nonnull EntityLivingBase player) {
		if (player instanceof EntityPlayer && player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null)) {
			if (player.world.isRemote) {
				return;
			}
			IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
			if (shield.getTimeWithoutDamage() >= shieldType.getShieldRechargeDelay() && shield.getCurrentHp() < shield.getMaxHp()) {
				if (ticksSinceLastRecharge < shieldType.getShieldRechargeRate()) {
					ticksSinceLastRecharge++;
				} else {
					ticksSinceLastRecharge = 0;
					rechargeShield(shield, stack, (EntityPlayer) player);
					updateClient((EntityPlayer) player, shield);
					triggerShieldEffect((EntityPlayer) player, stack, null, 0f, EffectTrigger.RECHARGE);
					if (shield.getCurrentHp() >= shield.getMaxHp()) {
						triggerShieldEffect((EntityPlayer) player, stack, null, 0f, EffectTrigger.FILLED);
					}
				}
			} else {
				shield.setTimeWithoutDamage(shield.getTimeWithoutDamage() + 1);
				if (shield.getCurrentHp() >= shield.getMaxHp()) {
					if (onTickEventTrigger >= 20) {
						onTickEventTrigger = 0;
						triggerShieldEffect((EntityPlayer) player, stack, null, 0f, EffectTrigger.FULL);
						updateClient((EntityPlayer) player, shield);
					} else {
						onTickEventTrigger++;
					}
				}
			}
		}
	}

	@Override
	public void onEquipped(@Nonnull ItemStack itemstack, @Nonnull EntityLivingBase player) {
		if (player instanceof EntityPlayer && player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null) && !player.world.isRemote) {
			IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
			shield.setMaxHp(shieldType.getMaxShieldHp());
			shield.setCurrentHp(0);
			shield.setTimeWithoutDamage(0);
			MinecraftForge.EVENT_BUS.post(new ShieldEquippedEvent((EntityPlayer) player, shield));
			if (!player.world.isRemote) {
				updateClient((EntityPlayer) player, shield);
			}
		}
	}

	@Override
	public void onUnequipped(@Nonnull ItemStack itemstack, @Nonnull EntityLivingBase player) {
		if (player instanceof EntityPlayer && player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null) && !player.world.isRemote) {
			IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
			shield.setMaxHp(0f);
			shield.setCurrentHp(0f);
			shield.setTimeWithoutDamage(0);
			updateClient((EntityPlayer) player, shield);
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("superiorshields.tooltip.hp") + " " + shieldType.getMaxShieldHp() + " " + I18n.format("superiorshields.tooltip.hpDetail"));
		tooltip.add(I18n.format("superiorshields.tooltip.rechargeDelay") + " " + (float) shieldType.getShieldRechargeDelay() / 20 + " " + I18n
				.format("superiorshields.tooltip.rechargeDelayTime"));
		tooltip.add(I18n.format("superiorshields.tooltip.rechargeRate") + " " + 1f / ((float) shieldType.getShieldRechargeRate() / 20) + " " + I18n
				.format("superiorshields.tooltip.rechargeRateTime"));

		if (!(shieldType.getEffect() instanceof ShieldEffectNone)) {
			tooltip.add(shieldType.getEffect().getDescription());
		}
	}

	protected void updateClient(@Nonnull PlayerEntity player, @Nonnull IShieldCapability shield) {
		if (player instanceof ServerPlayerEntity) {
			PacketHandler.INSTANCE.sendTo(new PacketShieldUpdate(shield.getCurrentHp(), shield.getMaxHp()), (ServerPlayerEntity) player);
		}
	}

	public void triggerShieldEffect(@Nonnull PlayerEntity player, @Nonnull ItemStack stack, @Nullable DamageSource source, float damage, EffectTrigger trigger) {
		if (player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).isPresent()) {
			IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).orElseGet(() -> null);
			shieldType.getEffect().applyEffect(shield, player, source, damage, trigger);
		}
	}
}
