package com.example.networkdemo.domain.interf

import com.example.networkdemo.example.model.dto.ArticleDto
import com.example.networkdemo.example.model.ui.ArticleUI
import com.example.networkdemo.model.VeriKeyDto
import com.example.networkdemo.remote.state.ResultState
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow

interface BackendRepo {
    fun getSignKey(): Flow<ResultState<VeriKeyDto>>
}