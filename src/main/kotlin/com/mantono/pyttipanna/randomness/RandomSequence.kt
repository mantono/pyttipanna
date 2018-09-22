package com.mantono.pyttipanna.randomness

import java.security.SecureRandom
import java.util.*

private const val LOWER_CASE: String = "abcdefghijklmnopqrstuvwxyz"
private val UPPER_CASE: String = LOWER_CASE.toUpperCase()
private const val DIGITS = "0123456789"
private const val SPECIAL_CHARS = "!@#%/=-_$[](){}£^*+;:<>,."

private val SEED_SOURCE: Array<Char> = "$DIGITS$LOWER_CASE$UPPER_CASE$SPECIAL_CHARS".toCharArray().toTypedArray()
val MAX_ENTROPY: Byte = SEED_SOURCE.lastIndex.toByte()

private val rand: Random = SecureRandom.getInstance("SHA1PRNG")

const val BINARY: Byte = 2
const val HEX: Byte = 16
val NO_SPECIAL_CHARS: Byte = (DIGITS.length + LOWER_CASE.length + UPPER_CASE.length).toByte()

fun generate(length: Int = 16, entropy: Byte = MAX_ENTROPY): CharSequence {
	val chars = randomChar(entropy)
		.take(length)
		.toList()
		.toCharArray()

	return String(chars)
}

fun randomChar(entropy: Byte = MAX_ENTROPY): Sequence<Char> = generateSequence {
	val max: Int = entropy.coerceIn(1, MAX_ENTROPY).toInt()
	val index: Int = rand.nextInt(max)
	SEED_SOURCE[index]
}

fun randomByte(): Sequence<Byte> = generateSequence {
	val array: ByteArray = ByteArray(1) { 0 }
	rand.nextBytes(array)
	array[0]
}


fun randomBytes(bytes: Int): ByteArray = ByteArray(bytes).apply {
	rand.nextBytes(this)
}