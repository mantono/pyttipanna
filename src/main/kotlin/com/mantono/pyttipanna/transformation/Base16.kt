package com.mantono.pyttipanna.transformation

private typealias ApacheBase16 = org.apache.commons.codec.binary.Hex

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class Base16(override val bytes: ByteArray) : Bytes {
	constructor(base16: String) : this(ApacheBase16.decodeHex(base16))

	override fun toString(): String = ApacheBase16.encodeHexString(bytes)

	companion object {
		private val hexRegex = Regex("^([\\da-f][\\da-f])*\$")

		fun validate(s: String): Boolean = s.matches(hexRegex)
	}
}

fun ByteArray.toBase16(): Base16 = Base16(this)