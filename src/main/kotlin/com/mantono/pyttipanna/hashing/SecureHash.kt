package com.mantono.pyttipanna.hashing

import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

const val DEFAULT_HASH_ITERATIONS = 200
const val DEFAULT_HASH_LENGTH = 64

fun secureHash(
	data: String,
	salt: ByteArray,
	iterations: Int = DEFAULT_HASH_ITERATIONS,
	length: Int = DEFAULT_HASH_LENGTH
): ByteArray = secureHash(data.toCharArray(), salt, iterations, length)

fun secureHash(
	data: ByteArray,
	salt: ByteArray,
	iterations: Int = DEFAULT_HASH_ITERATIONS,
	length: Int = DEFAULT_HASH_LENGTH
): ByteArray = secureHash(String(data).toCharArray(), salt, iterations, length)

/**
 * @param data the secret that should be hashed
 * @param salt is the salt that is used to make the generated
 * hash unique for the given input (_data_)
 * @param iterations the number of iterations used when generating
 * the hash. Default value is [DEFAULT_HASH_ITERATIONS]
 * @param length of the generated hash, in bytes. Will throw an [IllegalArgumentException]
 * if the number is too low to be considered secure. Currently this number is
 * set to 8 bytes (64 bits) and default value is [DEFAULT_HASH_LENGTH]
 */
fun secureHash(
	data: CharArray,
	salt: ByteArray,
	iterations: Int = DEFAULT_HASH_ITERATIONS,
	length: Int = DEFAULT_HASH_LENGTH
): ByteArray {
	require(length >= 4) { "Length must be at least 4, but it was $length" }
	val keySpec = PBEKeySpec(data, salt, iterations, length * 8)
	val keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
	return keyFactory.generateSecret(keySpec).encoded
}