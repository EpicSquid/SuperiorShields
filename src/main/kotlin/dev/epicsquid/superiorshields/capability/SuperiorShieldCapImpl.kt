package dev.epicsquid.superiorshields.capability

import net.minecraft.nbt.CompoundTag
import net.minecraftforge.common.util.INBTSerializable

data class SuperiorShieldCapImpl(
	override var hp: Int = 0,
	override var ticksWithoutDamage: Int = 0,
	override var ticksSinceRecharge: Int = 0,
	override var ticksFull: Int = 0,
) : SuperiorShieldCap, INBTSerializable<CompoundTag> {
	companion object {
		private const val HP = "hp"
		private const val TICKS_WITHOUT_DAMAGE = "ticks"
		private const val TICKS_SINCE_RECHARGE = "recharge"
		private const val TICKS_FULL = "full"
	}

	override fun serializeNBT(): CompoundTag =
		CompoundTag().apply {
			putInt(HP, hp)
			putInt(TICKS_WITHOUT_DAMAGE, ticksWithoutDamage)
			putInt(TICKS_SINCE_RECHARGE, ticksSinceRecharge)
			putInt(TICKS_FULL, ticksFull)
		}

	override fun deserializeNBT(nbt: CompoundTag) =
		nbt.run {
			hp = getInt(HP)
			ticksWithoutDamage = getInt(TICKS_WITHOUT_DAMAGE)
			ticksSinceRecharge = getInt(TICKS_SINCE_RECHARGE)
			ticksFull = getInt(TICKS_FULL)
		}
}

fun SuperiorShieldCap.absorbDamage(damage: Int): Int {
	// Reset the ticks without damage
	ticksWithoutDamage = 0
	return if (damage > hp) {
		// We are not able to absorb all the damage, return what's left
		hp = 0
		damage - hp
	} else {
		// We can absorb the damage, return 0
		hp -= damage
		0
	}
}
