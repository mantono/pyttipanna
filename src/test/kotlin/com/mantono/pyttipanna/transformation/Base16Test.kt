package com.mantono.pyttipanna.transformation

import com.mantono.pyttipanna.randomness.randomBytes
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class Base16Test
{
	@Test
	fun testFromByteArrayToStringAndBack()
	{
		byteArrayToStringToBytes<Base16> { b -> Base16(b) }
	}
}