package com.example.networkdemo.example

import com.example.networkdemo.example.model.dto.ArticleDto
import com.example.networkdemo.example.model.ui.ArticleUI
import com.skydoves.sandwich.ApiResponse

interface ExampleIRepo {
    suspend fun fetchArticle(): ApiResponse<ArticleDto>
}