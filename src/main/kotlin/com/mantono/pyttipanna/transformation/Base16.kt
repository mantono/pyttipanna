package com.mantono.pyttipanna.transformation

import org.apache.commons.codec.binary.Hex as ApacheBase16

object Base16 : BytesTransformer {
	private val hexRegex = Regex("^([\\da-f][\\da-f])*\$")

	override fun asString(b: ByteArray): String = ApacheBase16.encodeHexString(b)
	override fun isValid(s: String): Boolean = hexRegex.matches(s)
	override fun asBytes(s: String): ByteArray = ApacheBase16.decodeHex(s)
}