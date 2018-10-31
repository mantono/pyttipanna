package com.mantono.pyttipanna.transformation

import org.apache.commons.codec.binary.Base64 as ApacheBase64

private const val CHARS = "A-Za-z0-9\\-_"

object Base64UrlSafe : BytesTransformer {
	private val base64Regex =
		Regex("^(?:[$CHARS]{2})+\$")

	override fun asString(b: ByteArray): String =
		ApacheBase64.encodeBase64URLSafeString(b)

	override fun isValid(s: String): Boolean = base64Regex.matches(s)
	override fun asBytes(s: String): ByteArray = ApacheBase64.decodeBase64(s)
}