package com.mantono.pyttipanna

import java.nio.charset.Charset
import java.security.MessageDigest

enum class HashAlgorithm(val hashFunction: String, val instance: MessageDigest = MessageDigest.getInstance(hashFunction))
{
	MD5("MD5"),
	SHA1("SHA-1"),
	SHA256("SHA-256"),
	SHA384("SHA-384"),
	SHA512("SHA-512")
}

fun hash(data: String, salt: String, algorithm: HashAlgorithm = HashAlgorithm.SHA512): ByteArray
{
	return hash(data.toUtf8Bytes(), salt.toUtf8Bytes(), algorithm)
}

fun hash(data: String, algorithm: HashAlgorithm = HashAlgorithm.SHA512): ByteArray
{
	val utf8 = Charset.forName("UTF-8")
	return hash(data.toByteArray(utf8), algorithm = algorithm)
}

fun hash(data: ByteArray, salt: ByteArray = emptyByteArray, algorithm: HashAlgorithm = HashAlgorithm.SHA512): ByteArray
{
	val digest = algorithm.instance
	digest.update(salt)
	return digest.digest(data)
}

private val emptyByteArray: ByteArray = emptyArray<Byte>().toByteArray()

fun String.toUtf8Bytes(): ByteArray
{
	val utf8 = Charset.forName("UTF-8")
	return toByteArray(utf8)
}