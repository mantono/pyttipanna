package com.mantono.pyttipanna.transformation

import java.util.*

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class Base16(override val bytes: ByteArray): Bytes
{
	override fun equals(other: Any?): Boolean = this.isEqual(other)
	override fun hashCode(): Int = Arrays.hashCode(bytes)
	override fun toString(): String = bytes.asSequence()
			.map(::hexList)
			.flatMap { it.asSequence() }
			.toList()
			.toCharArray()
			.let { String(it) }
}

private fun hexList(byte: Byte): List<Char>
{
	val x = (byte.toInt() shr 4) and 0xF
	val y = byte.toInt() and 0xF
	val charX = Character.forDigit(x, 16)
	val charY = Character.forDigit(y, 16)
	return listOf(charX, charY)
}

fun ByteArray.toBase16(): Base16 = Base16(this)