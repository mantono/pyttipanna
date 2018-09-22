package com.mantono.pyttipanna

import kotlinx.coroutines.delay
import java.security.SecureRandom
import java.util.*
import java.util.concurrent.TimeUnit

suspend fun randomDelay(
	maxDelay: Int = 50,
	unit: TimeUnit = TimeUnit.MILLISECONDS,
	random: Random = SecureRandom()
): Int {
	val sleep = random.nextInt(maxDelay)
	delay(sleep.toLong(), unit)
	return sleep
}