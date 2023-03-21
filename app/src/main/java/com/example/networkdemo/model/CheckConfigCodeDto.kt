package com.example.networkdemo.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CheckConfigCodeDto(
    val code: Int = 0,
    val msg: String = "",
    val data: Any? = null,
    val sign: String = "",
    val timestamp: Long = 0,
)
