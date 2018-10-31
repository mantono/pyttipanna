package com.mantono.pyttipanna.transformation

import org.apache.commons.codec.binary.Base32 as ApacheBase32

object Base32 : BytesTransformer {
	private val base32Regex =
		Regex("^(?:[A-Z2-7]{8})*(?:[A-Z2-7]{2}={6}|[A-Z2-7]{4}={4}|[A-Z2-7]{5}={3}|[A-Z2-7]{7}=)?\$")

	override fun asString(b: ByteArray): String = ApacheBase32().encodeToString(b)
	override fun isValid(s: String): Boolean = base32Regex.matches(s)
	override fun asBytes(s: String): ByteArray = ApacheBase32().decode(s)

}