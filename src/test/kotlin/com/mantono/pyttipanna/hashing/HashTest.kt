package com.mantono.pyttipanna.hashing

import com.mantono.pyttipanna.transformation.Base16
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class HashTest {
	@Test
	fun testHashNoSalt() {
		val s: ByteArray = hash("test", HashAlgorithm.SHA512)
		val x: String = Base16.asString(s)

		val hashSha512 =
			"ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff"
		assertEquals(hashSha512, x)
	}

	@Test
	fun testHashEmptySalt() {
		val s: ByteArray = hash("test", "", HashAlgorithm.SHA512)
		val x: String = Base16.asString(s)

		val hashSha512 =
			"ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff"
		assertEquals(hashSha512, x)
	}

	@Test
	fun testHashWithSalt() {
		val s: ByteArray = hash("test", "some salt", HashAlgorithm.SHA512)
		val x: String = Base16.asString(s)

		val hashSha512 =
			"ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff"
		assertNotEquals(hashSha512, x)
	}

	@Test
	fun testHashNoStateOnMessageDigest() {
		val algorithm: HashAlgorithm = HashAlgorithm.SHA256
		val bytes = hash("test", "some salt", algorithm)
		algorithm.instance().update(bytes)
		val s0: ByteArray = hash("test", "some salt", algorithm)
		val x0: String = Base16.asString(s0)
		val s1: ByteArray = hash("test", "some salt", algorithm)
		val x1: String = Base16.asString(s1)
		assertEquals(x0, x1)
	}
}