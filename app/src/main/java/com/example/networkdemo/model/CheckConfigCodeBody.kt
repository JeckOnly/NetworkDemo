package com.example.networkdemo.model

import com.google.gson.annotations.JsonAdapter
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CheckConfigCodeBody(
    val timestamp: Long,
    val sign: String,
    val body: CheckConfigCodeBodybody
)

@JsonClass(generateAdapter = true)
data class CheckConfigCodeBodybody(
    val code: String
)

