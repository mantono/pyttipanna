package com.mantono.pyttipanna.transformation

import java.util.*

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class Base64(override val bytes: ByteArray): Bytes
{
	constructor(base64: String): this(java.util.Base64.getDecoder().decode(base64))
	override fun equals(other: Any?): Boolean = this.isEqual(other)
	override fun hashCode(): Int = Arrays.hashCode(bytes)
	override fun toString(): String = java.util.Base64
			.getEncoder()
			.encodeToString(bytes)
}

fun ByteArray.toBase64(): Base64 = Base64(this)