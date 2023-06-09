package com.example.networkdemo.domain.interf

import com.example.networkdemo.example.model.dto.ArticleDto
import com.example.networkdemo.example.model.ui.ArticleUI
import com.example.networkdemo.model.*
import com.example.networkdemo.remote.state.ResultState
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow

interface BackendRepo {
    fun getSignKey(): Flow<ResultState<VeriKeyDto>>

    fun getConfigCode(configCodeBody: ConfigCodeBody): Flow<ResultState<ConfigCodeDto>>

    fun checkConfigCode(checkConfigCodeBody: CheckConfigCodeBody): Flow<ResultState<CheckConfigCodeDto>>
}