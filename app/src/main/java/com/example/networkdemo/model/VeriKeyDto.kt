package com.example.networkdemo.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VeriKeyDto(
    @Json(name = "code")
    val code: Int,
    @Json(name = "data")
    val `data`: String,
    @Json(name = "msg")
    val msg: Any?
)