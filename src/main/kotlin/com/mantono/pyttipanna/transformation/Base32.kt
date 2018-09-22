package com.mantono.pyttipanna.transformation

import org.apache.commons.codec.binary.Base32 as ApacheBase32

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class Base32(override val bytes: ByteArray): Bytes
{
	constructor(base32: String): this(ApacheBase32().decode(base32))
	override fun toString(): String = ApacheBase32().encodeToString(bytes)

	companion object
	{
		private val base32Regex = Regex("^(?:[A-Z2-7]{8})*(?:[A-Z2-7]{2}={6}|[A-Z2-7]{4}={4}|[A-Z2-7]{5}={3}|[A-Z2-7]{7}=)?\$")
		fun validate(s: String): Boolean = s.matches(base32Regex)
	}
}

fun ByteArray.toBase32(): Base32 = Base32(this)