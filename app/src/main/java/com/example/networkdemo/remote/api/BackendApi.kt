package com.example.networkdemo.remote.api

import com.example.networkdemo.model.*
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

    @Headers("Content-Type: application/json")
    @POST("/api/v1/config/checkRtmpConfigCode")
    suspend fun checkConfigCode(@Body checkConfigCodeBody: CheckConfigCodeBody ): CheckConfigCodeDto

    companion object {
        /**
         * fixme getConfigCode 404
         */
        const val TEST_HOST = "http://192.168.70.79/"
        const val FORMAL_HOST = "https://device-api.szmoma-tech.com/"
    }
}