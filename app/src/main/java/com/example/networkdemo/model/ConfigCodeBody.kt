package com.example.networkdemo.model

import com.google.gson.annotations.JsonAdapter
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConfigCodeBody(
    val timestamp: Long,
    val sign: String,
)
