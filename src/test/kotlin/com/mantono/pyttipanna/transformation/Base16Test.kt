package com.mantono.pyttipanna.transformation

import org.junit.jupiter.api.Test

class Base16Test {
	@Test
	fun testFromByteArrayToStringAndBack() {
		testByteTransformer(functionUnderTest = Base16)
	}
}