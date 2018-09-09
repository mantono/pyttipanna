package com.mantono.pyttipanna.transformation

import java.security.MessageDigest

interface Bytes
{
	val bytes: ByteArray

	override fun equals(other: Any?): Boolean

	fun isEqual(other: Any?): Boolean = when(other)
	{
		null -> false
		is String -> MessageDigest.isEqual(other.toByteArray(), bytes)
		is Bytes -> MessageDigest.isEqual(other.bytes, bytes)
		else -> false
	}
}