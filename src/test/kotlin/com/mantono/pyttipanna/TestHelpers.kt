package com.mantono.pyttipanna

import java.time.Instant
import java.util.*


internal fun assertArrayNotEquals(b0: ByteArray, b1: ByteArray)
{
	assert(!Arrays.equals(b0, b1)) {
		"Content of $b0 and $b1 are equal"
	}
}

internal inline fun clockTime(repeat: Int = 200, funcToTime: () -> Unit): Long
{
	return (0 until repeat)
			.map {
				val start = Instant.now().toEpochMilli()
				funcToTime()
				val end = Instant.now().toEpochMilli()
				end - start
			}
			.average()
			.toLong()
}