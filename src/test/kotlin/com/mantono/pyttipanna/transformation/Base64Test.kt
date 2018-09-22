package com.mantono.pyttipanna.transformation

import org.junit.jupiter.api.Test

class Base64Test
{
	@Test
	fun testFromByteArrayToStringAndBack()
	{
		byteArrayToStringToBytes<Base64> { b -> Base64(b) }
	}
}