package com.example.crc_android.util

import android.util.Base64
import java.security.spec.AlgorithmParameterSpec
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


object AES256 {
    //키값 32바이트: AES256(24: AES192, 16: AES128)
    var secretKey = "01234567890123450123456789012345"
    var ivBytes = "0123456789012345".toByteArray()

    //AES256 암호화
    fun aesEncode(str: String): String? {
        try {
            val textBytes = str.toByteArray(charset("UTF-8"))
            val ivSpec: AlgorithmParameterSpec = IvParameterSpec(ivBytes)
            val newKey = SecretKeySpec(secretKey.toByteArray(charset("UTF-8")), "AES")
            var cipher: Cipher? = null
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec)
            return Base64.encodeToString(cipher.doFinal(textBytes), Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return str
    }

    //AES256 복호화
    fun aesDecode(str: String?): String? {
        try {
            val textBytes: ByteArray = Base64.decode(str, Base64.DEFAULT)
            val ivSpec: AlgorithmParameterSpec = IvParameterSpec(ivBytes)
            val newKey = SecretKeySpec(secretKey.toByteArray(charset("UTF-8")), "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec)
            return String(cipher.doFinal(textBytes), charset("UTF-8"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return str
    }
}