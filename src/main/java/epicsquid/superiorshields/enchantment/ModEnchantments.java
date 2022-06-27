package epicsquid.superiorshields.enchantment;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.EnchantmentBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import epicsquid.superiorshields.SuperiorShields;
import epicsquid.superiorshields.item.SuperiorShield;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ModEnchantments {

	public static final EnchantmentCategory type = EnchantmentCategory.create("superior_shield", item -> item instanceof SuperiorShield);
	private static final Registrate REGISTRATE = SuperiorShields.registrate();

	public static final RegistryEntry<CapacityEnchantment> CAPACITY = enchantmentBuilder("capacity", new CapacityEnchantment(Enchantment.Rarity.COMMON, type)).lang("Capacity").register();
	public static final RegistryEntry<CapacityEnchantment> JUMP_START = enchantmentBuilder("jump_start", new CapacityEnchantment(Enchantment.Rarity.RARE, type)).lang("Jump Start").register();
	public static final RegistryEntry<CapacityEnchantment> QUICKENED = enchantmentBuilder("quickened", new CapacityEnchantment(Enchantment.Rarity.COMMON, type)).lang("Quickened").register();

	private static <T extends ShieldEnchantment> EnchantmentBuilder<T, Registrate> enchantmentBuilder(String name, T enchantment) {
		return REGISTRATE.enchantment(name, type, (r, c, s) -> enchantment);
	}
}
