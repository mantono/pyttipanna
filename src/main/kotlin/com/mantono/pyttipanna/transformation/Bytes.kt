package com.mantono.pyttipanna.transformation

import java.security.MessageDigest

interface Bytes {
	val bytes: ByteArray

	override fun equals(other: Any?): Boolean

	fun isEqual(other: Any?): Boolean = when(other) {
		null -> false
		is String -> MessageDigest.isEqual(other.toByteArray(), bytes)
		is Bytes -> MessageDigest.isEqual(other.bytes, bytes)
		is ByteArray -> MessageDigest.isEqual(other, bytes)
		else -> false
	}

	companion object {
		inline fun <reified T : Bytes> fromString(s: String): T = when {
			Base16.validate(s) -> Base16(s) as T
			Base32.validate(s) -> Base32(s) as T
			Base64.validate(s) -> Base64(s) as T
			else -> throw UnsupportedOperationException("Class ${T::class} is not supported")
		}
	}
}