package com.example.networkdemo.remote.api

import com.example.networkdemo.model.ConfigCodeBody
import com.example.networkdemo.model.ConfigCodeDto
import com.example.networkdemo.model.VeriKeyDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface BackendApi {

    @GET("api/v1/sign/getSignKey")
    suspend fun getSignKey(

    ): VeriKeyDto

    @Headers("Content-Type: application/json")
    @POST("/api/v1/config/getRtmpConfigCode")
    suspend fun getConfigCode(@Body configCodeBody: ConfigCodeBody): ConfigCodeDto
}