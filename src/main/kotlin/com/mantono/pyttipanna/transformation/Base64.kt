package com.mantono.pyttipanna.transformation

private typealias ApacheBase64 = org.apache.commons.codec.binary.Base64

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class Base64(override val bytes: ByteArray) : Bytes {
	constructor(base64: String) : this(java.util.Base64.getDecoder().decode(base64))

	override fun toString(): String = java.util.Base64
		.getEncoder()
		.encodeToString(bytes)

	companion object {
		fun validate(s: String): Boolean = ApacheBase64.isBase64(s)
	}
}

fun ByteArray.toBase64(): Base64 = Base64(this)