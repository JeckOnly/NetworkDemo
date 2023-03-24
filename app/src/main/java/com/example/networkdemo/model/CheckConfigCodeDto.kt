package com.example.networkdemo.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CheckConfigCodeDto(
    val code: Int = 0,
    val msg: String = "",
    val data: CheckConfigCodeDataDto? = null,
    val sign: String = "",
    val timestamp: Long = 0,
)

@JsonClass(generateAdapter = true)
data class CheckConfigCodeDataDto(
    val deviceSn: String = "",
    val code: String = "",
    val expireAt: Long = 0L,
    val state: Int = 0,
    val config: CheckConfigCodeDataConfigDto? = null
)


@JsonClass(generateAdapter = true)
data class CheckConfigCodeDataConfigDto(
    val platform: Int = 0,
    val rtmpUrl: String = "",
    val name: String = "",
    val secret: String = "",
)
