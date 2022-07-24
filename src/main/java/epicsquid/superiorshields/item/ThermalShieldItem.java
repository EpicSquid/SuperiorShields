package epicsquid.superiorshields.item;

import cofh.lib.item.IAugmentableItem;
import cofh.lib.util.constants.NBTTags;
import cofh.lib.util.helpers.AugmentDataHelper;
import cofh.lib.util.helpers.AugmentableHelper;
import epicsquid.superiorshields.shield.IEnergyShield;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.IntSupplier;

public class ThermalShieldItem extends EnergyShieldItem implements IAugmentableItem {

	private final IntSupplier numSlots = () -> 1;


	public ThermalShieldItem(Properties props, IEnergyShield shieldType) {
		super(props, shieldType);
	}

	@Override
	public int getAugmentSlots(ItemStack augmentable) {
		return numSlots.getAsInt();
	}

	@Override
	public boolean validAugment(ItemStack augmentable, ItemStack newAugment, List<ItemStack> augments) {
		// TODO: allow energy augs
		String newType = AugmentDataHelper.getAugmentType(newAugment);
		// Allow base mods
		if (!newType.isEmpty() && !newType.equals(NBTTags.TAG_AUGMENT_TYPE_UPGRADE)) {
			return false;
		}
		// No duplicates
		for (ItemStack augment : augments) {
			if (newType.equals(AugmentDataHelper.getAugmentType(augment))) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void updateAugmentState(ItemStack augmentable, List<ItemStack> augments) {
		// TODO: allow energy augs
		augmentable.getOrCreateTag().put(NBTTags.TAG_PROPERTIES, new CompoundTag());
		for (ItemStack augment : augments) {
			CompoundTag augmentData = AugmentDataHelper.getAugmentData(augment);
			if (augmentData == null) {
				continue;
			}
			setShieldFromAugment(augmentable, augmentData);
		}
	}

	@Override
	public float getShieldCapacity(@Nonnull ItemStack stack) {
		return super.getShieldCapacity(stack) + (2.0f * (getBaseModLevel(stack) - 1.0f));
	}

	@Override
	public int getShieldDelay(@Nonnull ItemStack stack) {
		return super.getShieldDelay(stack) - ((10 * ((int) getBaseModLevel(stack))) - 10);
	}

	@Override
	public int getShieldRate(@Nonnull ItemStack stack) {
		return super.getShieldRate(stack) - ((15 * ((int) getBaseModLevel(stack))) - 15);
	}

	private float getBaseModLevel(@Nonnull ItemStack stack) {
		return AugmentableHelper.getPropertyWithDefault(stack, NBTTags.TAG_AUGMENT_BASE_MOD, 1.0f);
	}

	private void setShieldFromAugment(ItemStack augmentable, CompoundTag augmentData) {
		// Set the shield type based on what base augment is in
		CompoundTag subTag = augmentable.getTagElement(NBTTags.TAG_PROPERTIES);
		if (subTag == null) {
			return;
		}
		AugmentableHelper.setAttributeFromAugmentMax(subTag, augmentData, NBTTags.TAG_AUGMENT_BASE_MOD);
	}
}
