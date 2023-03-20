package com.example.networkdemo.remote.api

import com.example.networkdemo.model.VeriKeyDto
import retrofit2.http.GET

interface BackendApi {

    @GET("api/v1/sign/getSignKey")
    suspend fun getSignKey(

    ): VeriKeyDto

}