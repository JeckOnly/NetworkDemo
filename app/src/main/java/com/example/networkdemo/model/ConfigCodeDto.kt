package com.example.networkdemo.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConfigCodeDto(
    val code: Int = 0,
    val msg: String = "",
    val data: ConfigCodeData? = null,
    val sign: String = "",
    val timestamp: Long = 0,
)

@JsonClass(generateAdapter = true)
data class ConfigCodeData(
    // 配置码
    @Json(name = "code")
    val code: String,
    @Json(name = "config")
    val config: Any?,
    @Json(name = "deviceSn")
    val deviceSn: String,
    @Json(name = "expireAt")
    val expireAt: Long,
    @Json(name = "state")
    val state: Int
)
