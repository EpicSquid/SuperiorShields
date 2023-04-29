package dev.epicsquid.superiorshields.network

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import net.minecraft.network.FriendlyByteBuf

@OptIn(ExperimentalSerializationApi::class)
class FriendlyByteBufDecoder(
	private val input: FriendlyByteBuf,
	private var elementsCount: Int = 0
) : AbstractDecoder() {
	private var elementIndex = 0
	override val serializersModule: SerializersModule = EmptySerializersModule()
	override fun decodeBoolean(): Boolean = input.readByte().toInt() != 0
	override fun decodeByte(): Byte = input.readByte()
	override fun decodeShort(): Short = input.readShort()
	override fun decodeInt(): Int = input.readInt()
	override fun decodeLong(): Long = input.readLong()
	override fun decodeFloat(): Float = input.readFloat()
	override fun decodeDouble(): Double = input.readDouble()
	override fun decodeChar(): Char = input.readChar()
	override fun decodeString(): String = String(input.readByteArray())
	override fun decodeEnum(enumDescriptor: SerialDescriptor): Int = input.readInt()

	override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
		if (elementIndex == elementsCount) return CompositeDecoder.DECODE_DONE
		return elementIndex++
	}

	override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder =
		FriendlyByteBufDecoder(input, descriptor.elementsCount)

	override fun decodeSequentially(): Boolean = true

	override fun decodeCollectionSize(descriptor: SerialDescriptor): Int =
		decodeInt().also { elementsCount = it }

	override fun decodeNotNullMark(): Boolean = decodeBoolean()
}

fun <T> decodeFrom(input: FriendlyByteBuf, deserializer: DeserializationStrategy<T>): T {
	val decoder = FriendlyByteBufDecoder(input)
	return decoder.decodeSerializableValue(deserializer)
}

inline fun <reified T> decodeFrom(input: FriendlyByteBuf): T = decodeFrom(input, serializer())