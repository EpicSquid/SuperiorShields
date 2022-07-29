package epicsquid.superiorshields.enchantment;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.EnchantmentBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import epicsquid.superiorshields.SuperiorShields;
import epicsquid.superiorshields.item.ISuperiorShield;
import epicsquid.superiorshields.lang.ModLang;
import epicsquid.superiorshields.shield.effect.ShieldEffectFireNova;
import epicsquid.superiorshields.shield.effect.ShieldEffectPotionNova;
import epicsquid.superiorshields.shield.effect.ShieldEffectSpikePotion;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ModEnchantments {

	public static final EnchantmentCategory type = EnchantmentCategory.create("superior_shield", item -> item instanceof ISuperiorShield);
	private static final Registrate REGISTRATE = SuperiorShields.registrate();

	public static final RegistryEntry<CapacityEnchantment> CAPACITY = enchantmentBuilder("capacity", new CapacityEnchantment(Enchantment.Rarity.COMMON, type)).lang("Shield Capacity").register();
	public static final RegistryEntry<RagingEnchantment> RAGING = enchantmentBuilder("raging", new RagingEnchantment(Enchantment.Rarity.RARE, type)).lang("Raging").register();
	public static final RegistryEntry<AmplifyEnchantment> AMPLIFY = enchantmentBuilder("amplify", new AmplifyEnchantment(Enchantment.Rarity.RARE, type)).lang("Amplify").register();
	public static final RegistryEntry<CuringEnchantment> CURING = enchantmentBuilder("curing", new CuringEnchantment(Enchantment.Rarity.RARE, type)).lang("Curing").register();
	public static final RegistryEntry<QuickenedEnchantment> QUICKENED = enchantmentBuilder("quickened", new QuickenedEnchantment(Enchantment.Rarity.COMMON, type)).lang("Quickened").register();

	public static final RegistryEntry<NovaShieldEnchantment> FIRE_NOVA = enchantmentBuilder("fire_nova", new NovaShieldEnchantment(
									Enchantment.Rarity.UNCOMMON, type,
									new ShieldEffectFireNova()
					)
	).lang("Fire Nova").register();

	public static final RegistryEntry<NovaShieldEnchantment> FROST_NOVA = enchantmentBuilder("frost_nova", new NovaShieldEnchantment(
									Enchantment.Rarity.UNCOMMON, type,
									new ShieldEffectPotionNova(ModLang.FROST_NOVA.getKey(), MobEffects.WEAKNESS, MobEffects.MOVEMENT_SLOWDOWN)
					)
	).lang("Frost Nova").register();

	public static final RegistryEntry<NovaShieldEnchantment> SHULKING_NOVA = enchantmentBuilder("shulking_nova", new NovaShieldEnchantment(
									Enchantment.Rarity.RARE, type,
									new ShieldEffectPotionNova(ModLang.SHULKING_NOVA.getKey(), MobEffects.LEVITATION)
					)
	).lang("Shulking Nova").register();

	public static final RegistryEntry<SpikeShieldEnchantment> POISON_SPIKES = enchantmentBuilder("poison_spikes", new SpikeShieldEnchantment(
									Enchantment.Rarity.UNCOMMON, type,
									new ShieldEffectSpikePotion(ModLang.POISON_SPIKES.getKey(), MobEffects.POISON)
					)
	).lang("Poison Spikes").register();

	public static final RegistryEntry<SpikeShieldEnchantment> WITHER_SPIKES = enchantmentBuilder("wither_spikes", new SpikeShieldEnchantment(
									Enchantment.Rarity.UNCOMMON, type,
									new ShieldEffectSpikePotion(ModLang.WITHER_SPIKES.getKey(), MobEffects.WITHER)
					)
	).lang("Withering Spikes").register();

	private static <T extends ShieldEnchantment> EnchantmentBuilder<T, Registrate> enchantmentBuilder(String name, T enchantment) {
		return REGISTRATE.enchantment(name, type, (r, c, s) -> enchantment);
	}

	public static void classload() {
	}
}
