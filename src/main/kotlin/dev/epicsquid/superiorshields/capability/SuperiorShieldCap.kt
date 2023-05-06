package dev.epicsquid.superiorshields.capability

interface SuperiorShieldCap {
	var hp: Int // TODO make this a double/float
	var ticksWithoutDamage: Int
	var ticksSinceRecharge: Int
	var ticksFull: Int
}