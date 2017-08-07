package com.mantono.pyttipanna

import kotlinx.coroutines.experimental.delay
import java.security.SecureRandom
import java.util.*

suspend fun randomDelay(maxDelayMs: Int = 300): Int
{
	val r: Random = SecureRandom()
	val sleep = r.nextInt(maxDelayMs)
	delay(sleep.toLong())
	return sleep
}