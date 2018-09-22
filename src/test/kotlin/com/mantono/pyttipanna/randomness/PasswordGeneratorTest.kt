package com.mantono.pyttipanna.randomness

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PasswordGeneratorTest {
	@Test
	fun testBinaryOnly() {
		val x = generate(16, BINARY)
		val binaryRegex = Regex("[01]{16}")
		assertTrue(x.matches(binaryRegex))
	}

	@Test
	fun testHexOnly() {
		val x = generate(16, HEX)
		val hexRegex = Regex("[0-9abcdef]{16}")
		assertTrue(x.matches(hexRegex))
	}


	@Test
	fun testNoSpecialChars() {
		val x = generate(16, NO_SPECIAL_CHARS)
		val letterDigitsRegex = Regex("[\\w]{16}")
		assertTrue(x.matches(letterDigitsRegex))
	}

	@Test
	fun testLength() {
		val x = generate(64)
		assertEquals(64, x.length)
	}

	@Test
	fun testOverflowProtection() = generate(entropy = Byte.MAX_VALUE)

	@Test
	fun testUnderflowProtection() = generate(entropy = Byte.MIN_VALUE)
}