package com.example.networkdemo.domain.impl

import com.example.networkdemo.domain.interf.BackendRepo
import com.example.networkdemo.model.*
import com.example.networkdemo.remote.api.BackendApi
import com.example.networkdemo.remote.state.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BackendRepoImpl @Inject constructor(
    private val backendApi: BackendApi
) : BackendRepo {
    override fun getSignKey(): Flow<ResultState<VeriKeyDto>> = flow<ResultState<VeriKeyDto>> {
        try {
            emit(ResultState.Loading(true))
            val veriKeyDto = backendApi.getSignKey()
            emit(ResultState.Success(data = veriKeyDto))
        } catch (e: Throwable) {
            emit(ResultState.Failure(throwable = e))

        } finally {
            emit(ResultState.Loading(false))
        }

    }.flowOn(Dispatchers.IO)

    override fun getConfigCode(configCodeBody: ConfigCodeBody): Flow<ResultState<ConfigCodeDto>> = flow {

        try {
            emit(ResultState.Loading(true))
            val configCodeDto = backendApi.getConfigCode(configCodeBody)
            emit(ResultState.Success(data = configCodeDto))
        } catch (e: Throwable) {
            emit(ResultState.Failure(throwable = e))

            } finally {
                emit(ResultState.Loading(false))
            }
        }.flowOn(Dispatchers.IO)


    override fun checkConfigCode(checkConfigCodeBody: CheckConfigCodeBody): Flow<ResultState<CheckConfigCodeDto>> =
        flow {
            try {
                emit(ResultState.Loading(true))
                val checkConfigDto = backendApi.checkConfigCode(checkConfigCodeBody)
                emit(ResultState.Success(data = checkConfigDto))
            } catch (e: Throwable) {
                emit(ResultState.Failure(throwable = e))

            } finally {
                emit(ResultState.Loading(false))
            }
        }.flowOn(Dispatchers.IO)

}