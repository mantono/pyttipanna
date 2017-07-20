package com.mantono.www.security

import java.security.MessageDigest

enum class Algorithm(val hashFunction: String, val instance: MessageDigest = MessageDigest.getInstance(hashFunction))
{
	MD5("MD5"),
	SHA1("SHA-1"),
	SHA256("SHA-256"),
	SHA384("SHA-384"),
	SHA512("SHA-512")
}

fun hash(data: String, salt: String, algorithm: Algorithm = Algorithm.SHA512): ByteArray
{
	return hash(data + salt, algorithm)
}

fun hash(data: String, algorithm: Algorithm = Algorithm.SHA512): ByteArray
{
	return hash(data.toByteArray(), algorithm)
}

fun hash(data: ByteArray, algorithm: Algorithm = Algorithm.SHA512): ByteArray
{
	val digest = algorithm.instance
	digest.update(data)
	return digest.digest()
}
