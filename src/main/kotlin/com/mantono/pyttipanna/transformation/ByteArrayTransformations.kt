package com.mantono.pyttipanna.transformation

import org.apache.commons.codec.binary.Base32

fun ByteArray.toHex(): String
{
	val s = asSequence()
			.map(::hexList)
			.flatMap { it.asSequence() }
			.toList()
			.toCharArray()

	return String(s)
}

fun ByteArray.toBase32(): String = Base32().encodeAsString(this)

fun ByteArray.toBase64(): String
{
	val encoded: ByteArray = java.util.Base64.getEncoder().encode(this)
	return String(encoded)
}

private fun hexList(byte: Byte): List<Char>
{
	val x = (byte.toInt() shr 4) and 0xF
	val y = byte.toInt() and 0xF
	val charX = Character.forDigit(x, 16)
	val charY = Character.forDigit(y, 16)
	return listOf(charX, charY)
}