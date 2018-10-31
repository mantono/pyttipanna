package com.mantono.pyttipanna.transformation

import org.junit.jupiter.api.Test

class Base64Test {
	@Test
	fun testFromByteArrayToStringAndBack() {
		testByteTransformer(functionUnderTest = Base64)
	}
}