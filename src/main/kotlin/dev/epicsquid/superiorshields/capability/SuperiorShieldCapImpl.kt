package dev.epicsquid.superiorshields.capability

import net.minecraft.nbt.CompoundTag
import net.minecraftforge.common.util.INBTSerializable

data class SuperiorShieldCapImpl(
	override var hp: Int = 0,
	override var ticksWithoutDamage: Int = 0
) : SuperiorShieldCap, INBTSerializable<CompoundTag> {
	companion object {
		private const val HP = "hp"
		private const val TICKS_WITHOUT_DAMAGE = "ticks"
	}

	override fun serializeNBT(): CompoundTag =
		CompoundTag().apply {
			putInt(HP, hp)
			putInt(TICKS_WITHOUT_DAMAGE, ticksWithoutDamage)
		}

	override fun deserializeNBT(nbt: CompoundTag) =
		nbt.run {
			hp = getInt(HP)
			ticksWithoutDamage = getInt(TICKS_WITHOUT_DAMAGE)
		}
}