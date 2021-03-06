package com.mantono.pyttipanna.transformation

import org.apache.commons.codec.binary.Base64 as ApacheBase64

private const val CHARS = "A-Za-z0-9+/"

object Base64 : BytesTransformer {
	private val base64Regex =
		Regex("^(?:[$CHARS]{4})*(?:[$CHARS]{2}==|[$CHARS]{3}=|[$CHARS]{4})\$")

	override fun asString(b: ByteArray): String = ApacheBase64.encodeBase64String(b)
	override fun isValid(s: String): Boolean = base64Regex.matches(s)
	override fun asBytes(s: String): ByteArray = ApacheBase64.decodeBase64(s)
}