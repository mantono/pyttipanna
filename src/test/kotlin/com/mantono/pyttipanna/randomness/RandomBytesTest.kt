package com.mantono.pyttipanna.randomness

import com.mantono.pyttipanna.assertArrayNotEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RandomBytesTest {
	@Test
	fun testRandomBytesAreUnique() {
		assertArrayNotEquals(randomBytes(10), randomBytes(10))
	}

	@Test
	fun testRandomBytesCorrectLength() {
		assertEquals(20, randomBytes(20).size)
	}
}