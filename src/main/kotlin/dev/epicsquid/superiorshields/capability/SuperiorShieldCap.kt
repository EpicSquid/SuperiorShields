package dev.epicsquid.superiorshields.capability

interface SuperiorShieldCap {
	var hp: Int
	var ticksWithoutDamage: Int
	var ticksSinceRecharge: Int
	var ticksFull: Int
	var rechargeRate: Int
	var rechargeDelay: Int
	var capacity: Int
}