package epicsquid.superiorshields.item;

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
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.network.NetworkDirection;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.List;

public class SuperiorShieldItem<T extends ShieldType> extends Item implements SuperiorShield<T>, ICurio {

    private int ticksSinceLastRecharge = 0;
    private T shieldType;

    // Used to ensure the potion effect is not applied every tick
    private int onTickEventTrigger = 0;

    public SuperiorShieldItem(Item.Properties props, T shieldType) {
        super(props.stacksTo(1).durability(shieldType.getMaxDamage()));
        this.shieldType = shieldType;
    }

    @Override
    public T getShield() {
        return shieldType;
    }

    @Override
    public float applyShield(Player player, ItemStack stack, float damage, DamageSource source) {
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

    /**
     * Triggers the use of energy to recharge.
     *
     * @param stack The stack to get the capability to recharge from.
     * @return true if there was enough energy to recharge.
     */
    protected boolean useEnergyToRecharge(ItemStack stack, Player player) {
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
                if (livingEntity instanceof Player) {
                    Player player = (Player) livingEntity;
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
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        tooltip.add(new TranslatableComponent("superiorshields.tooltip.blank"));
        tooltip.add(new TranslatableComponent("superiorshields.tooltip.equip").withStyle(ChatFormatting.GRAY));
        tooltip.add(new TranslatableComponent("superiorshields.tooltip.hp", df.format(ShieldHelper.getShieldCapacity(stack))).withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(new TranslatableComponent("superiorshields.tooltip.recharge_rate", df.format((float) shieldType.getShieldRechargeRate() / 20f)).withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(new TranslatableComponent("superiorshields.tooltip.recharge_delay", df.format((float) shieldType.getShieldRechargeDelay() / 20f)).withStyle(ChatFormatting.DARK_GREEN));
    }

    protected void updateClient(Player player, IShieldCapability shield) {
        if (player instanceof ServerPlayer) {
            NetworkHandler.INSTANCE.sendTo(new SPacketShieldUpdate(shield.getCurrentHp(), shield.getMaxHp()), ((ServerPlayer) player).connection.connection, NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    public void triggerShieldEffect(Player player, ItemStack stack, @Nullable DamageSource source, float damage, EffectTrigger trigger) {
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
    public void equip(Player player, ItemStack stack) {
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
    public void unequip(Player player) {
        if (player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).isPresent() && !player.getCommandSenderWorld().isClientSide) {
            IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).orElseGet(() -> null);
            shield.setMaxHp(0f);
            shield.setCurrentHp(0f);
            shield.setTimeWithoutDamage(0);
            updateClient(player, shield);
        }
    }
}
