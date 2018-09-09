package com.mantono.pyttipanna.transformation

import com.mantono.pyttipanna.randomness.randomBytes
import org.junit.jupiter.api.Test
import java.util.*

class Base16Test
{
	@Test
	fun testFromByteArrayToStringAndBack() {
		val original: ByteArray = randomBytes(16)
		val b16 = Base16(original)
	}
}