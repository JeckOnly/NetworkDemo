package com.example.networkdemo.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignKeyBody(
    @Json(name = "deviceModel")
    val deviceModel: String,
    @Json(name = "deviceSn")
    val deviceSn: String,
    @Json(name = "packageCode")
    val packageCode: String,
    @Json(name = "versionName")
    val versionName: String,
    @Json(name = "versionValue")
    val versionValue: Int
)

val EXAMPLE_SIGN_KEY_BODY = SignKeyBody(
    deviceModel = "emulator_pixel_2",
    deviceSn = "fuckingDeviceSn",
    packageCode = "com.example.networkdemo",
    versionName = "1.0.0",
    versionValue = 1
)