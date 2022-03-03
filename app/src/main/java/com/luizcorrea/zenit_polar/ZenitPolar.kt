package com.luizcorrea.zenit_polar

import java.lang.StringBuilder
import kotlin.collections.HashMap

object ZenitPolar {
    private var ALPHA = arrayOf(
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
        "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
        "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
        "é","í","ó","á","ê","î","ô","â","ẽ","ĩ","õ","ã",
        "É","Í","Ó","Á","Ê","Î","Ô","Â","Ẽ","Ĩ","Õ","Ã"
    )
    private var ZENITPOLAR = arrayOf(
        "i", "b","c","d","o","f","g","h","a","j","k","n","m",
        "l","e","z","q","t","s","r","u", "v","w","x","y","p",
        "I", "B","C","D","O","F","G","H","A","J","K","N","M",
        "L","E","Z","Q","T","S","R","U", "V","W","X","Y","P",
        "6","7","8","9","0","1","2","3","4","5",
        "ó","á","é","í","ô","â","ê","î","õ","ã","ẽ","ĩ",
        "Ó","Á","É","Í","Ô","Â","Ê","Î","Õ","Ã","Ẽ","Ĩ"

    )
    private var ENCRYPT: HashMap<String, String> = HashMap()
    private var DECRYPT: HashMap<String, String> = HashMap()


    fun encrypt(encryptCode: String): String {
        val builder = StringBuilder()
        val words = encryptCode.trim { it <= ' ' }.split(" ").toTypedArray()
        for (word in words) {
            for (i in word.indices) {
                val encrypt = ENCRYPT[word.substring(i, i + 1)]
                builder.append(encrypt).append("")
            }
            builder.append(" ")
        }
        return builder.toString()
    }

    init {
        var i = 0
        while (i < ALPHA.size && i < ZENITPOLAR.size) {
            ENCRYPT[ALPHA[i]] = ZENITPOLAR[i]
            DECRYPT[ZENITPOLAR[i]] = ALPHA[i]
            i++
        }
    }
}