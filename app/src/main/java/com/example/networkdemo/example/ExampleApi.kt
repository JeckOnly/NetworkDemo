package com.example.networkdemo.example

import com.example.networkdemo.example.model.dto.ArticleDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface ExampleApi {


    @GET("article/list/0/json")
    suspend fun fetchArticle(

    ): ApiResponse<ArticleDto>

    @GET("article/list/0/json")
    suspend fun fetchArticle2(

    ): ArticleDto


}