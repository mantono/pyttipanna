package com.mantono.pyttipanna

import com.mantono.pyttipanna.hashing.HashAlgorithm
import com.mantono.pyttipanna.hashing.hash
import com.mantono.pyttipanna.transformation.toHex
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals

class HashTest
{
	@Test
	fun testHashNoSalt()
	{
		val s = hash("test", HashAlgorithm.SHA512)
		val x = s.toHex()

		val hashSha512 = "ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff"
		assertEquals(hashSha512, x)
	}

	@Test
	fun testHashEmptySalt()
	{
		val s = hash("test", "", HashAlgorithm.SHA512)
		val x = s.toHex()

		val hashSha512 = "ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff"
		assertEquals(hashSha512, x)
	}

	@Test
	fun testHashWithSalt()
	{
		val s = hash("test", "some salt", HashAlgorithm.SHA512)
		val x = s.toHex()

		val hashSha512 = "ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff"
		assertNotEquals(hashSha512, x)
	}
}