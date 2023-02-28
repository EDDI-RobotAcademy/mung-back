package com.example.mungmung.utility.encrypt

import com.example.mungmung.utility.encrypt.exception.UnableToGenerateHash
import java.math.BigInteger
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object EncryptionUtil {
    private const val COUNT_OF_ITERATION = 1000
    private const val LENGTH_OF_SALT = 16
    private const val RADIX = 16
    private const val LENGTH_OF_KEY = 64 * 8
    private const val PBKDF2_HMAC_SHA1 = "PBKDF2WithHmacSHA1"
    private const val SECURE_RND = "SHA1PRNG"
    private const val DELIMITER = ":"
    fun generateHash(plain: String?): String {
        return try {
            val charSequence = plain?.toCharArray()
            val salt = salt
            val spec = PBEKeySpec(charSequence, salt, COUNT_OF_ITERATION, LENGTH_OF_KEY)
            val skf = SecretKeyFactory.getInstance(PBKDF2_HMAC_SHA1)
            val hash = skf.generateSecret(spec).encoded
            COUNT_OF_ITERATION.toString() + ":" + toHex(salt) + ":" + toHex(hash)
        } catch (e: NoSuchAlgorithmException) {
            throw UnableToGenerateHash("Unsupported Algorithm!", e)
        } catch (e: InvalidKeySpecException) {
            throw UnableToGenerateHash("Unsupported Key Spec on this system!", e)
        }
    }

    fun checkValidation(plainToCheck: String?, originHash: String?): Boolean {
        val splited = originHash?.split(DELIMITER.toRegex())?.dropLastWhile { it.isEmpty() }?.toTypedArray()
        val iteration = splited?.get(0)?.toInt()
        val salt = fromHex(splited!![1])
        val hash = fromHex(splited[2])
        return try {
            val spec = iteration?.let { PBEKeySpec(plainToCheck?.toCharArray(), salt, it, hash.size * 8) }
            val skf = SecretKeyFactory.getInstance(PBKDF2_HMAC_SHA1)
            val test = skf.generateSecret(spec).encoded
            checkDiff(hash, test)
        } catch (e: NoSuchAlgorithmException) {
            throw UnableToGenerateHash("Unsupported Algorithm!", e)
        } catch (e: InvalidKeySpecException) {
            throw UnableToGenerateHash("Unsupported Key Spec on this system!", e)
        }
    }

    private fun checkDiff(hash: ByteArray, test: ByteArray): Boolean {
        val hl = hash.size
        val tl = test.size
        var diff = hl xor tl
        for (i in 0 until hl) {
            diff = diff or (hash[i].toInt() xor test[i].toInt())
        }
        return diff == 0
    }

    @get:Throws(NoSuchAlgorithmException::class)
    private val salt: ByteArray
        private get() {
            val sr = SecureRandom.getInstance(SECURE_RND)
            val salt = ByteArray(LENGTH_OF_SALT)
            sr.nextBytes(salt)
            return salt
        }

    private fun toHex(array: ByteArray): String {
        val bi = BigInteger(1, array)
        val hex = bi.toString(RADIX)
        val paddingLength = array.size * 2 - hex.length
        return if (paddingLength > 0) "0".repeat(paddingLength) + hex else hex
    }

    private fun fromHex(hex: String): ByteArray {
        val bytes = ByteArray(hex.length / 2)
        for (i in bytes.indices) {
            bytes[i] = hex.substring(2 * i, 2 * i + 2).toInt(RADIX).toByte()
        }
        return bytes
    }
}