package epicsquid.superiorshields.capability.shield;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ShieldCapabilityStorage implements IStorage<IShieldCapability> {

	public static final String NBT_MAX_SHIELD_HP = "maxShieldHp";
	public static final String NBT_SHIELD_HP = "shieldHp";
	public static final String NBT_TIME_WITHOUT_DAMAGE = "timeWithoutDamage";

	@Override
	public INBT writeNBT(Capability<IShieldCapability> capability, IShieldCapability instance, Direction side) {
		CompoundNBT tag = new CompoundNBT();
		tag.putFloat(NBT_MAX_SHIELD_HP, instance.getMaxHp());
		tag.putFloat(NBT_SHIELD_HP, instance.getCurrentHp());
		tag.putInt(NBT_TIME_WITHOUT_DAMAGE, instance.getTimeWithoutDamage());
		return tag;
	}

	@Override
	public void readNBT(Capability<IShieldCapability> capability, IShieldCapability instance, Direction side, INBT nbt) {
		if (nbt instanceof CompoundNBT) {
			CompoundNBT tag = (CompoundNBT) nbt;
			if (tag.contains(NBT_MAX_SHIELD_HP)) {
				instance.setMaxHp(tag.getFloat(NBT_MAX_SHIELD_HP));
			}
			if (tag.contains(NBT_SHIELD_HP)) {
				instance.setCurrentHp(tag.getFloat(NBT_SHIELD_HP));
			}
			if (tag.contains(NBT_TIME_WITHOUT_DAMAGE)) {
				instance.setTimeWithoutDamage(tag.getInt(NBT_TIME_WITHOUT_DAMAGE));
			}
		}
	}
}
