package com.example.networkdemo.util

object EncryptUtil {

    /**
     * 第一步拼接字符串
     */
    fun spliceSignStr(signKey: String, timeStamp: String, bodyString: String): String {
        return "${signKey}${timeStamp}${bodyString}${signKey}"
    }


}