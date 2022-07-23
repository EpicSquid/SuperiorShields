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
import vazkii.botania.api.BotaniaAPI;

public class ModTags extends ItemTagsProvider {

	public static final TagKey<Item> MANAPEARL = ItemTags.create(new ResourceLocation("forge", "mana_pearl"));
	public static final TagKey<Item> PIXIE_DUST = ItemTags.create(new ResourceLocation("forge", "pixie_dust"));

	public static final TagKey<Item> FLUX_COIL = ItemTags.create(new ResourceLocation("forge", "flux_coil"));
	public ModTags(DataGenerator dataGen, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(dataGen, blockTagsProvider, SuperiorShields.MODID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		// Add Botania items for crafting
		tag(MANAPEARL).addOptional(new ResourceLocation(BotaniaAPI.MODID, "mana_pearl"));
		tag(PIXIE_DUST).addOptional(new ResourceLocation(BotaniaAPI.MODID, "pixie_dust"));
		tag(FLUX_COIL).addOptional(new ResourceLocation("thermal", "rf_coil"));
	}
}
