package epicsquid.superiorshields;

import epicsquid.superiorshields.capability.shield.CapabilityRegistry;
import epicsquid.superiorshields.item.SuperiorShield;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.event.CurioChangeEvent;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = SuperiorShields.MODID)
public class EventManager {

    @SubscribeEvent
    public static void onLivingHurtEvent(@Nonnull LivingHurtEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (CuriosApi.getCuriosHelper().getCuriosHandler(player).isPresent() && event.getSource() != DamageSource.STARVE && event.getSource() != DamageSource.DROWN) {
                ICuriosItemHandler handler = CuriosApi.getCuriosHelper().getCuriosHandler(player).orElse(null);
                handler.getStacksHandler(SuperiorShields.SHIELD_CURIO).ifPresent(
                        stackHandler -> {
                            ItemStack stack = stackHandler.getStacks().getStackInSlot(0);
                            if (!stack.isEmpty() && stack.getItem() instanceof SuperiorShield) {
                                event.setAmount(((SuperiorShield) stack.getItem()).applyShield(player, stack, event.getAmount(), event.getSource()));
                            }
                        }
                );
            }
        }
    }

    @SubscribeEvent
    public static void onCurioChangeEvent(CurioChangeEvent event) {
        if (event.getIdentifier().equals(CapabilityRegistry.ShieldCapabilityName) && event.getEntityLiving() instanceof Player && !event.getFrom().sameItemStackIgnoreDurability(event.getTo())) {
            Player player = (Player) event.getEntityLiving();
            if (event.getFrom().getItem() instanceof SuperiorShield<?>) {
                // Unequip
                ((SuperiorShield<?>) event.getFrom().getItem()).unequip(player);
            } else if (event.getTo().getItem() instanceof SuperiorShield<?>) {
                // Equip
                ((SuperiorShield<?>) event.getTo().getItem()).equip(player, event.getTo());
            }
        }
    }

}
