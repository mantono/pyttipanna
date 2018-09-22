package com.mantono.pyttipanna.transformation

import org.junit.jupiter.api.Test

class Base32Test {
	@Test
	fun testFromByteArrayToStringAndBack() {
		byteArrayToStringToBytes<Base32> { b -> Base32(b) }
	}
}