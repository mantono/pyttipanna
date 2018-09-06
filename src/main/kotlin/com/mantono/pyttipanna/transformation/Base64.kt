package com.mantono.pyttipanna.transformation

fun ByteArray.toBase64(): String
{
	val encoded: ByteArray = java.util.Base64.getEncoder().encode(this)
	return String(encoded)
}