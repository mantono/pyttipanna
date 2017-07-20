package com.mantono.www.security

import com.mantono.mysqltools.query
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import org.apache.commons.validator.routines.EmailValidator
import java.security.SecureRandom
import java.util.*

interface AccountManager
{
	fun createAccount(username: String, address: EmailAddress, password: String, repeatedPassword: String): Boolean
	fun emailAddressExists(address: EmailAddress): Boolean
	fun userExists(username: String): Boolean
	fun permitLogin(username: String, password: String): Boolean
	fun userToken(username: String): CharSequence
}

suspend fun randomDelay(maxDelayMs: Int = 300): Int
{
	val r: Random = SecureRandom()
	val sleep = r.nextInt(maxDelayMs)
	delay(sleep.toLong())
	return sleep
}

object accounts: AccountManager
{
	override fun emailAddressExists(address: EmailAddress): Boolean
	{
		return query{
			val hashedEmailBytes: ByteArray = hash(address.address)
			val hashedEmail = String(hashedEmailBytes)
			val statement = prepareStatement("SELECT COUNT(email) as `hits` FROM `User` WHERE email = ?")
			statement.setString(1, hashedEmail)
			val result = statement.executeQuery()
			result.next()
			val count = result.getString("hits")
			count != "0"
		}
	}

	override fun userExists(username: String): Boolean
	{
		return query{
			val statement = prepareStatement("SELECT COUNT(`login`) as `hits` FROM `User` WHERE `login` = ?")
			statement.setString(1, username)
			val result = statement.executeQuery()
			result.next()
			val count = result.getString("hits")
			count != "0"
		}
	}

	override fun permitLogin(username: String, password: String): Boolean
	{
		if(!userExists(username))
			return false
		val salt: String = userSalt(username)
		val hash = String(hash(password, salt))
		val hashInDatabase: String = dbPasswordHash(username)
		return runBlocking{
			val match = hash == hashInDatabase
			print(hashInDatabase)
			randomDelay()
			print(hash)
			return@runBlocking match
		}
	}

	private fun dbPasswordHash(username: String): String = fieldForUser(username, "password")
	fun userSalt(username: String): String = fieldForUser(username, "salt")
	override fun userToken(username: String): CharSequence = fieldForUser(username, "token")

	private fun fieldForUser(username: String, field: String): String
	{
		return query {
			val statement = prepareStatement("SELECT `$field` FROM `User` WHERE `login` = ?")
			statement.setString(1, username)
			val result = statement.executeQuery()
			result.next()
			result.getString(field)
		}
	}

	override fun createAccount(username: String, address: EmailAddress, password: String, repeatedPassword: String): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

}

private val validator: EmailValidator = EmailValidator.getInstance()
fun isValidEmail(address: String): Boolean = validator.isValid(address)

data class EmailAddress(val address: String)
{
	init
	{
		if(!isValidEmail(address))
			throw IllegalArgumentException("Invalid email address: $address")
	}
}
