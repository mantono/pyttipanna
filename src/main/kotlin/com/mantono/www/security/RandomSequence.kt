package com.mantono.www.security

import java.security.SecureRandom
import java.util.*

private const val LOWER_CASE: String = "abcdefghijklmnopqrstuvwxyz"
private val UPPER_CASE: String = LOWER_CASE.toUpperCase()
private const val DIGITS = "0123456789"
private const val SPECIAL_CHARS = "!@#%/=-_$[](){}£^*+;:<>,."

private val SEED_SOURCE: Array<Char> = "$DIGITS$LOWER_CASE$UPPER_CASE$SPECIAL_CHARS".toCharArray().toTypedArray()
private val LAST_INDEX: Byte = SEED_SOURCE.lastIndex.toByte()

private val rand: Random = SecureRandom()

const val BINARY: Byte = 2
const val HEX: Byte = 16
val NO_SPECIAL_CHARS: Byte = (DIGITS.length + LOWER_CASE.length + UPPER_CASE.length).toByte()

fun generate(length: Int = 16, entropy: Byte = LAST_INDEX): CharSequence
{
	val chars = randomChar(entropy)
			.take(length)
			.toList()
			.toCharArray()

	return String(chars)
}

fun randomChar(entropy: Byte = LAST_INDEX): Sequence<Char> = generateSequence()
{
	val max: Int = entropy.coerceIn(1, LAST_INDEX).toInt()
	val index: Int = rand.nextInt(max)
	SEED_SOURCE[index]
}