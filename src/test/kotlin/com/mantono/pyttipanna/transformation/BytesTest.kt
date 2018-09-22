package com.mantono.pyttipanna.transformation

import com.mantono.pyttipanna.randomness.randomBytes
import org.junit.jupiter.api.Assertions.assertArrayEquals

internal inline fun <reified T: Bytes> byteArrayToStringToBytes(
		testInput: ByteArray = randomBytes(16),
		functionUnderTest: (ByteArray) -> Bytes
)
{
	val bytes: Bytes = functionUnderTest(testInput)
	val bytesStr = bytes.toString()
	val bytesAgain = Bytes.fromString<T>(bytesStr)
	assertArrayEquals(testInput, bytesAgain.bytes)
}