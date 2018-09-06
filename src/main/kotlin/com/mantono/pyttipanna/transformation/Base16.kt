package com.mantono.pyttipanna.transformation

fun ByteArray.toBase16(): String
{
	val s = asSequence()
			.map(::hexList)
			.flatMap { it.asSequence() }
			.toList()
			.toCharArray()

	return String(s)
}

private fun hexList(byte: Byte): List<Char>
{
	val x = (byte.toInt() shr 4) and 0xF
	val y = byte.toInt() and 0xF
	val charX = Character.forDigit(x, 16)
	val charY = Character.forDigit(y, 16)
	return listOf(charX, charY)
}