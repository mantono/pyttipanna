package com.mantono.pyttipanna.transformation

import com.mantono.pyttipanna.randomness.randomBytes
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BytesTest {
	@Test
	fun assertThrowsOnIncompatibleTypesOnFromString() {
		assertThrows<ClassCastException>
		{
			Bytes.fromString<Base32>("fcfad35c62ae")
		}
	}

	@Test
	fun assertThrowsOnInvalidInput() {
		assertThrows<UnsupportedOperationException>
		{
			Bytes.fromString<Base16>("#")
		}
	}

	@Test
	fun testIsEqualWithString() {
		val hexString = "eab5a3c7c04f"
		val hexBytes = Base16(hexString)
		assertTrue(hexBytes.isEqual(hexString))
	}

	@Test
	fun testIsEqualWithByteArray() {
		val hexString = "eab5a3c7c04f"
		val hexBytes = Base16(hexString)
		val hexByteArray: ByteArray = hexBytes.bytes
		assertTrue(hexBytes.isEqual(hexByteArray))
	}

	@Test
	fun testIsEqualWithByte16() {
		val hexString = "eab5a3c7c04f"
		val hexBytes1 = Base16(hexString)
		val hexBytes2 = Base16(hexString)
		assertTrue(hexBytes1.isEqual(hexBytes2))
	}
}

internal inline fun <reified T : Bytes> byteArrayToStringToBytes(
	testInput: ByteArray = randomBytes(16),
	functionUnderTest: (ByteArray) -> Bytes
) {
	val bytes: Bytes = functionUnderTest(testInput)
	val bytesStr = bytes.toString()
	val bytesAgain = Bytes.fromString<T>(bytesStr)
	assertArrayEquals(testInput, bytesAgain.bytes)
}