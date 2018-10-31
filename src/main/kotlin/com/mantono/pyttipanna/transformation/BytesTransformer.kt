package com.mantono.pyttipanna.transformation

import java.security.MessageDigest

/**
 * Transform [ByteArray] to [String] and [String] to [ByteArray]
 * for data encodings described in [RFC 4648](https://tools.ietf.org/html/rfc4648)
 */
interface BytesTransformer {
	fun asString(b: ByteArray): String
	fun isValid(s: String): Boolean
	fun asBytes(s: String): ByteArray
	operator fun invoke(s: String): ByteArray = asBytes(s)
	operator fun invoke(b: ByteArray): String = asString(b)
}

fun ByteArray.isEqual(other: ByteArray?): Boolean = MessageDigest.isEqual(this, other)