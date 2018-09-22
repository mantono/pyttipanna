package com.mantono.pyttipanna.hashing

import com.mantono.pyttipanna.assertArrayNotEquals
import com.mantono.pyttipanna.clockTime
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

private val pass1 = "my secret password"
private val pass2 = "my other secret password"
private val salt1 = "some data to be used as salt".toByteArray()
private val salt2 = "some other data to be used as salt".toByteArray()

class SecureHashTest
{
	@Test
	fun testSecureHashIsEqualsOnSameInputs()
	{
		val first: ByteArray = secureHash(pass1, salt1, 10, 30)
		val second: ByteArray = secureHash(pass1, salt1, 10, 30)
		assertArrayEquals(first, second)
	}

	@Test
	fun testSecureHashIsNotEqualOnDifferentPassword()
	{
		val b0 = secureHash(pass1, salt1, 100, 30)
		val b1 = secureHash(pass2, salt1, 100, 30)
		assertArrayNotEquals(b0, b1)
	}

	@Test
	fun testSecureHashIsNotEqualOnSameInputButDifferentSalt()
	{
		val b0 = secureHash(pass1, salt1, 100, 30)
		val b1 = secureHash(pass1, salt2, 100, 30)
		assertArrayNotEquals(b0, b1)
	}

	@Test
	fun testSecureHashIsNotEqualsOnSameInputsButDifferentIterations()
	{
		val tenIterations: ByteArray = secureHash(pass1, salt1, 10, 30)
		val elevenIterations: ByteArray = secureHash(pass1, salt1, 11, 30)
		assertArrayNotEquals(tenIterations, elevenIterations)
	}

	@Test
	fun testSecureHashIsNotEqualsOnSameInputsButDifferentLength()
	{
		val tenIterations: ByteArray = secureHash(pass1, salt1, 10, 30)
		val elevenIterations: ByteArray = secureHash(pass1, salt1, 10, 31)
		assertArrayNotEquals(tenIterations, elevenIterations)
	}

	@Test
	fun testThatIterationsIncreasesHashingTime()
	{
		val faster: Long = clockTime { secureHash(pass1, salt1, 300) }
		val slower: Long = clockTime { secureHash(pass1, salt1, 900) }
		assertTrue(faster < slower) { "$faster >= $slower" }
	}

	@Test
	fun testAssertCorrectLength()
	{
		assertLength(4)
		assertLength(10)
		assertLength(20)
		assertLength(30)
		assertLength(100)
	}

	@Test
	fun testAssertThrowsOnTooShortLength()
	{
		assertThrows<IllegalArgumentException> {
			assertLength(3)
		}
	}

	private fun assertLength(length: Int)
	{
		assertEquals(length, secureHash(pass1, salt1, 20, length).size)
	}
}