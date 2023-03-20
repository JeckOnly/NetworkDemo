package com.example.networkdemo.example

import com.example.networkdemo.example.model.dto.ArticleDto
import com.example.networkdemo.example.model.ui.ArticleUI
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow

interface ExampleIRepo {
    suspend fun fetchArticle(): ApiResponse<ArticleDto>

    suspend fun fetchArticle2(): Flow<MyResult<List<ArticleUI>>>
}