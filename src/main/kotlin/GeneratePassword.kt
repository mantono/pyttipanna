package com.mantono.pyttipanna

fun main(args: Array<String>)
{
	val length: Int = args.getSafe(0, 16)
	val entropy: Byte = args.getSafe(1, MAX_ENTROPY).toByte()
	println(generate(length, entropy))
}

private fun Array<String>.getSafe(i: Int, default: Number): Int
{
	if(i in indices)
		return Integer.parseInt(this[i])
	return default.toInt()
}
