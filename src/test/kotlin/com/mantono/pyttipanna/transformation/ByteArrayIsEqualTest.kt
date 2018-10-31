package com.mantono.pyttipanna.transformation

import com.mantono.pyttipanna.randomness.randomBytes
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ByteArrayIsEqualTest {
	@Test
	fun testIsEqualWithByte16() {
		val hexString = "eab5a3c7c04f"
		val hexBytes1 = Base16(hexString)
		val hexBytes2 = Base16(hexString)
		assertTrue(hexBytes1.isEqual(hexBytes2))
	}
}

internal fun testByteTransformer(
	testInput: ByteArray = randomBytes(16),
	functionUnderTest: BytesTransformer
) {
	val s: String = functionUnderTest(testInput)
	assertTrue(functionUnderTest.isValid(s), s)
	assertFalse(functionUnderTest.isValid(s.drop(1)), s.drop(1))
	assertFalse(functionUnderTest.isValid(s.plus("a")), s.plus("a"))
	val bytesAgain: ByteArray = functionUnderTest(s)
	assertArrayEquals(testInput, bytesAgain)
}