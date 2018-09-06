package com.mantono.pyttipanna.transformation

import org.apache.commons.codec.binary.Base32

fun ByteArray.toBase32(): String = Base32().encodeAsString(this)