package com.mantono.pyttipanna.transformation

import org.junit.jupiter.api.Test

class Base16Test {
	@Test
	fun testFromByteArrayToStringAndBack() {
		byteArrayToStringToBytes<Base16> { b -> Base16(b) }
	}
}