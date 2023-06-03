package dev.epicsquid.superiorshields.network

import kotlinx.serialization.Serializable

@Serializable
data class SuperiorShieldUpdatePacket(
	val hp: Int,
	val capacity: Int,
)