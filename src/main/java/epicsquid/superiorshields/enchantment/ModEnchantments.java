package epicsquid.superiorshields.enchantment;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.EnchantmentBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import epicsquid.superiorshields.SuperiorShields;
import epicsquid.superiorshields.item.ISuperiorShield;
import epicsquid.superiorshields.shield.effect.ShieldEffectFireNova;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ModEnchantments {

	public static final EnchantmentCategory type = EnchantmentCategory.create("superior_shield", item -> item instanceof ISuperiorShield);
	private static final Registrate REGISTRATE = SuperiorShields.registrate();

	public static final RegistryEntry<CapacityEnchantment> CAPACITY = enchantmentBuilder("capacity", new CapacityEnchantment(Enchantment.Rarity.COMMON, type)).lang("Shield Capacity").register();
	public static final RegistryEntry<CuringEnchantment> CURING = enchantmentBuilder("curing", new CuringEnchantment(Enchantment.Rarity.RARE, type)).lang("Curing").register();
	public static final RegistryEntry<QuickenedEnchantment> QUICKENED = enchantmentBuilder("quickened", new QuickenedEnchantment(Enchantment.Rarity.COMMON, type)).lang("Quickened").register();

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
