package dev.epicsquid.superiorshields.network

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import net.minecraft.network.FriendlyByteBuf

@OptIn(ExperimentalSerializationApi::class)
class FriendlyByteBufEncoder(private val buf: FriendlyByteBuf) : AbstractEncoder() {
	override val serializersModule: SerializersModule = EmptySerializersModule()
	override fun encodeBoolean(value: Boolean) {
		buf.writeByte(if (value) 1 else 0)
	}

	override fun encodeByte(value: Byte) {
		buf.writeByte(value.toInt())
	}

	override fun encodeShort(value: Short) {
		buf.writeShort(value.toInt())
	}

	override fun encodeInt(value: Int) {
		buf.writeInt(value)
	}

	override fun encodeLong(value: Long) {
		buf.writeLong(value)
	}

	override fun encodeFloat(value: Float) {
		buf.writeFloat(value)
	}

	override fun encodeDouble(value: Double) {
		buf.writeDouble(value)
	}

	override fun encodeChar(value: Char) {
		buf.writeChar(value.code)
	}

	override fun encodeString(value: String) {
		buf.writeByteArray(value.toByteArray())
	}

	override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) {
		buf.writeInt(index)
	}

	override fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
		encodeInt(collectionSize)
		return this
	}

	override fun encodeNull() = encodeBoolean(false)
	override fun encodeNotNullMark() = encodeBoolean(true)
}

fun <T> encodeTo(output: FriendlyByteBuf, serializer: SerializationStrategy<T>, value: T) {
	val encoder = FriendlyByteBufEncoder(output)
	encoder.encodeSerializableValue(serializer, value)
}

inline fun <reified T> encodeTo(output: FriendlyByteBuf, value: T) = encodeTo(output, serializer(), value)