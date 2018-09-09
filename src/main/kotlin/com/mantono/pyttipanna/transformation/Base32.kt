package com.mantono.pyttipanna.transformation

import org.apache.commons.codec.binary.Base32 as ApacheBase32
import java.util.*

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class Base32(override val bytes: ByteArray): Bytes
{
	constructor(base32: String): this(ApacheBase32().decode(base32))
	override fun equals(other: Any?): Boolean = this.isEqual(other)
	override fun hashCode(): Int = Arrays.hashCode(bytes)
	override fun toString(): String = ApacheBase32().encodeToString(bytes)
}

fun ByteArray.toBase32(): Base32 = Base32(this)