package epicsquid.superiorshields.tags;

import epicsquid.superiorshields.SuperiorShields;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ModTags extends ItemTagsProvider {

	public static final TagKey<Item> MANAPEARL = ItemTags.create(new ResourceLocation("forge", "mana_pearl"));
	public static final TagKey<Item> PIXIE_DUST = ItemTags.create(new ResourceLocation("forge", "pixie_dust"));

	public static final TagKey<Item> FLUX_COIL = ItemTags.create(new ResourceLocation("thermal", "flux_coil"));
	public static final TagKey<Item> COMPONENT_STEEL = ItemTags.create(new ResourceLocation("forge", "component_steel"));
	public static final TagKey<Item> WOODEN_GRIP = ItemTags.create(new ResourceLocation("forge", "wooden_grip"));
	public static final TagKey<Item> SOUL_STAINED_STEEL_INGOT = ItemTags.create(new ResourceLocation("forge", "ingots/soul_stained_steel"));
	public static final TagKey<Item> SPIRIT_FABRIC = ItemTags.create(new ResourceLocation("malum", "spirit_fabric"));
	public static final TagKey<Item> HALLOWED_SPIRIT_RESONATOR = ItemTags.create(new ResourceLocation("malum", "hallowed_spirit_resonator"));
	public static final TagKey<Item> STAINED_SPIRIT_RESONATOR = ItemTags.create(new ResourceLocation("malum", "stained_spirit_resonator"));
	public static final TagKey<Item> SOURCE_GEM = ItemTags.create(new ResourceLocation("forge", "gem/source"));

	public ModTags(DataGenerator dataGen, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(dataGen, blockTagsProvider, SuperiorShields.MODID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		// Add Botania items for crafting
		tag(MANAPEARL).addOptional(new ResourceLocation("botania", "mana_pearl"));
		tag(PIXIE_DUST).addOptional(new ResourceLocation("botania", "pixie_dust"));
		tag(FLUX_COIL).addOptional(new ResourceLocation("thermal", "rf_coil"));
		tag(COMPONENT_STEEL).addOptional(new ResourceLocation("immersiveengineering", "component_steel"));
		tag(WOODEN_GRIP).addOptional(new ResourceLocation("immersiveengineering", "wooden_grip"));
		tag(SOUL_STAINED_STEEL_INGOT).addOptional(new ResourceLocation("malum", "soul_stained_steel_ingot"));
		tag(SPIRIT_FABRIC).addOptional(new ResourceLocation("malum", "spirit_fabric"));
		tag(HALLOWED_SPIRIT_RESONATOR).addOptional(new ResourceLocation("malum", "hallowed_spirit_resonator"));
		tag(STAINED_SPIRIT_RESONATOR).addOptional(new ResourceLocation("malum", "stained_spirit_resonator"));
		tag(SOURCE_GEM).addOptional(new ResourceLocation("ars_nouveau", "source_gem"));
	}
}
