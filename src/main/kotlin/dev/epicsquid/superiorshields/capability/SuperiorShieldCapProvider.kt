package dev.epicsquid.superiorshields.capability

import dev.epicsquid.superiorshields.registry.CapabilityRegistry
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.common.util.LazyOptional

class SuperiorShieldCapProvider : ICapabilityProvider, INBTSerializable<CompoundTag> {

	private val shield = SuperiorShieldCapImpl()
	private val op: LazyOptional<SuperiorShieldCap> = LazyOptional.of { shield }

	override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> =
		CapabilityRegistry.SUPERIOR_SHIELD_CAP.orEmpty(cap, op)

	override fun serializeNBT(): CompoundTag = shield.serializeNBT()

	override fun deserializeNBT(nbt: CompoundTag) = shield.deserializeNBT(nbt)

}