package epicsquid.superiorshields.enchantment;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.EnchantmentBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import epicsquid.superiorshields.SuperiorShields;
import epicsquid.superiorshields.item.SuperiorShield;
import epicsquid.superiorshields.shield.effect.ShieldEffectFireNova;
import epicsquid.superiorshields.shield.effect.ShieldEffectKnockbackNova;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ModEnchantments {

	public static final EnchantmentCategory type = EnchantmentCategory.create("superior_shield", item -> item instanceof SuperiorShield);
	private static final Registrate REGISTRATE = SuperiorShields.registrate();

	public static final RegistryEntry<CapacityEnchantment> CAPACITY = enchantmentBuilder("capacity", new CapacityEnchantment(Enchantment.Rarity.COMMON, type)).lang("Shield Capacity").register();
	public static final RegistryEntry<QuickenedEnchantment> QUICKENED = enchantmentBuilder("quickened", new QuickenedEnchantment(Enchantment.Rarity.COMMON, type)).lang("Quickened").register();
	public static final RegistryEntry<NovaShieldEnchantment> KNOCKBACK_NOVA = enchantmentBuilder("knockback_nova", new NovaShieldEnchantment(
									Enchantment.Rarity.UNCOMMON, type,
									new ShieldEffectKnockbackNova(1.5, 1, 1, 1)
					)
	).lang("Knockback Nova").register();
	public static final RegistryEntry<NovaShieldEnchantment> FIRE_NOVA = enchantmentBuilder("fire_nova", new NovaShieldEnchantment(
									Enchantment.Rarity.UNCOMMON, type,
									new ShieldEffectFireNova(5, 1.5)
					)
	).lang("Fire Nova").register();

	private static <T extends ShieldEnchantment> EnchantmentBuilder<T, Registrate> enchantmentBuilder(String name, T enchantment) {
		return REGISTRATE.enchantment(name, type, (r, c, s) -> enchantment);
	}

	public static void classload() {
	}
}
