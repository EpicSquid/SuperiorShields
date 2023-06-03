package dev.epicsquid.superiorshields.capability

import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.item.ItemStack
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ForgeCapabilities
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.energy.IEnergyStorage

class EnergyCapProvider(
	private val maxEnergy: Int,
	private val inputRate: Int,
	private val outputRate: Int,
	private val stack: ItemStack
) : ICapabilityProvider, INBTSerializable<CompoundTag>, IEnergyStorage {
	private companion object {
		const val ENERGY_TAG = "energy"
	}

	private val energyStorage: LazyOptional<IEnergyStorage> = LazyOptional.of { this }
	private var currentEnergy: Int = stack.orCreateTag.getInt(ENERGY_TAG)

	override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> =
		if (cap == ForgeCapabilities.ENERGY) energyStorage.cast() else LazyOptional.empty()

	override fun serializeNBT(): CompoundTag =
		CompoundTag().apply {
			putInt(ENERGY_TAG, currentEnergy)
		}

	override fun deserializeNBT(nbt: CompoundTag) {
		currentEnergy = nbt.getInt(ENERGY_TAG)
	}

	override fun receiveEnergy(maxReceive: Int, simulate: Boolean): Int {
		val energyToUpdate = maxReceive.coerceAtMost(inputRate).coerceAtMost(maxEnergy - currentEnergy)
		if (!simulate) {
			currentEnergy += energyToUpdate
			stack.orCreateTag.putInt(ENERGY_TAG, currentEnergy)
		}
		return energyToUpdate
	}

	override fun extractEnergy(maxExtract: Int, simulate: Boolean): Int {
		val energyToUpdate = maxExtract.coerceAtMost(outputRate).coerceAtMost(currentEnergy)
		if (!simulate) {
			currentEnergy -= energyToUpdate
			stack.orCreateTag.putInt(ENERGY_TAG, currentEnergy)
		}
		return energyToUpdate
	}

	override fun getEnergyStored(): Int = currentEnergy

	override fun getMaxEnergyStored(): Int = maxEnergy

	override fun canExtract(): Boolean = true

	override fun canReceive(): Boolean = true
}