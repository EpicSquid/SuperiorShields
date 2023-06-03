package dev.epicsquid.superiorshields.capability

import net.minecraft.nbt.CompoundTag
import net.minecraftforge.common.util.INBTSerializable

data class SuperiorShieldCapImpl(
	override var hp: Int = 0,
	override var ticksWithoutDamage: Int = 0,
	override var ticksSinceRecharge: Int = 0,
	override var ticksFull: Int = 0,
	override var rechargeRate: Int = 0,
	override var rechargeDelay: Int = 0,
	override var capacity: Int = 0
) : SuperiorShieldCap, INBTSerializable<CompoundTag> {
	companion object {
		private const val HP = "hp"
		private const val TICKS_WITHOUT_DAMAGE = "ticks"
		private const val TICKS_SINCE_RECHARGE = "recharge"
		private const val TICKS_FULL = "full"
		private const val RECHARGE_RATE = "rate"
		private const val RECHARGE_DELAY = "delay"
		private const val CAPACITY = "capacity"
	}

	override fun serializeNBT(): CompoundTag =
		CompoundTag().apply {
			putInt(HP, hp)
			putInt(TICKS_WITHOUT_DAMAGE, ticksWithoutDamage)
			putInt(TICKS_SINCE_RECHARGE, ticksSinceRecharge)
			putInt(TICKS_FULL, ticksFull)
			putInt(RECHARGE_RATE, rechargeRate)
			putInt(RECHARGE_DELAY, rechargeDelay)
			putInt(CAPACITY, capacity)
		}

	override fun deserializeNBT(nbt: CompoundTag) =
		nbt.run {
			hp = getInt(HP)
			ticksWithoutDamage = getInt(TICKS_WITHOUT_DAMAGE)
			ticksSinceRecharge = getInt(TICKS_SINCE_RECHARGE)
			ticksFull = getInt(TICKS_FULL)
			rechargeRate = getInt(RECHARGE_RATE)
			rechargeDelay = getInt(RECHARGE_DELAY)
			capacity = getInt(CAPACITY)
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

fun SuperiorShieldCap.reset() {
	hp = 0
	capacity = 0
	ticksWithoutDamage = 0
	ticksSinceRecharge = 0
	ticksFull = 0
	rechargeRate = 0
	rechargeDelay = 0
}
