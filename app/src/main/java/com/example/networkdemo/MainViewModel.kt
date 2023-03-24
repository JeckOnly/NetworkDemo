package com.example.networkdemo

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkdemo.domain.interf.BackendRepo
import com.example.networkdemo.model.*
import com.example.networkdemo.remote.api.BackendApi
import com.example.networkdemo.remote.state.ResultState
import com.example.networkdemo.util.EncryptUtil
import com.example.networkdemo.util.toJson
import com.example.networkdemo.util.toMd5Str
import com.example.networkdemo.util.urlToQRCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val application: Application,
    private val backendRepo: BackendRepo
) : ViewModel() {

    private val mutableVeriKey: MutableStateFlow<VeriKeyDto?> = MutableStateFlow(null)

    private val mutableConfigCodeDto: MutableStateFlow<ConfigCodeDto?> = MutableStateFlow(null)

    private val _qrCode: MutableStateFlow<Drawable?> = MutableStateFlow(null)
    val qrCode: StateFlow<Drawable?> = _qrCode

    fun getVeriKey(doOnSuccess: () -> Unit, doOnFailure: () -> Unit) {
        viewModelScope.launch {
            backendRepo.getSignKey().collect {
                when (it) {
                    is ResultState.Failure -> {
                        Timber.d(it.throwable.stackTraceToString())
                        doOnFailure()
                    }
                    is ResultState.Loading -> {

                    }
                    is ResultState.Success -> {
                        Timber.d(it.data.toString())
                        mutableVeriKey.update { _ ->
                            it.data
                        }
                        doOnSuccess()
                    }
                }
            }
        }
    }

    fun getConfigCode(doOnSuccess: () -> Unit, doOnFailure: () -> Unit) {
        viewModelScope.launch {
            mutableVeriKey.value?.let { verikeyDto ->
                val timestamp = System.currentTimeMillis()

                val signTemp = EncryptUtil.spliceSignStr(
                    signKey = verikeyDto.data,
                    timeStamp = timestamp.toString(),
                    bodyString = "null"
                )
                Timber.d("时间戳 $timestamp")
                Timber.d("signTemp $signTemp")
                val sign = signTemp.toMd5Str()
                Timber.d("md5sign $sign")

                val configCodeBody = ConfigCodeBody(
                    timestamp = timestamp,
                    sign = sign
                )

                backendRepo.getConfigCode(
                    configCodeBody = configCodeBody
                ).onEach {
                    when (it) {
                        is ResultState.Failure -> {
                            Timber.e(it.throwable.stackTraceToString())
                            doOnFailure()
                        }
                        is ResultState.Loading -> {

                        }
                        is ResultState.Success -> {
                            Timber.d(it.data.toString())
                            mutableConfigCodeDto.update { _ ->
                                it.data
                            }
                            val netUrl = "${BackendApi.TEST_HOST}h5/config/rtmp?code=${mutableConfigCodeDto.value?.data?.code}"
                            Timber.d("网址：$netUrl")
                            _qrCode.update {
                                netUrl.urlToQRCode(application)
                            }


                            doOnSuccess()
                        }
                    }
                }.collect()
            }

        }
    }

    fun checkConfigCode(doOnSuccess: () -> Unit, doOnFailure: () -> Unit) {
        viewModelScope.launch {
            mutableVeriKey.value?.let { verikeyDto ->
                mutableConfigCodeDto.value?.data?.code?.let { configCode ->

                    val timestamp = System.currentTimeMillis()
                    val checkConfigCodeBodybody = CheckConfigCodeBodybody(code = configCode)
                    val signTemp = EncryptUtil.spliceSignStr(
                        signKey = verikeyDto.data,
                        timeStamp = timestamp.toString(),
                        bodyString = checkConfigCodeBodybody.toJson()
                    )
                    Timber.d("时间戳 $timestamp")
                    Timber.d("signTemp $signTemp")
                    val sign = signTemp.toMd5Str()
                    Timber.d("md5sign $sign")
                    val checkConfigCodeBody = CheckConfigCodeBody(
                        timestamp = timestamp,
                        sign = sign,
                        body = checkConfigCodeBodybody
                    )
                    backendRepo.checkConfigCode(
                        checkConfigCodeBody
                    ).onEach {
                        when (it) {
                            is ResultState.Failure -> {
                                Timber.d(it.throwable.stackTraceToString())
                                doOnFailure()
                            }
                            is ResultState.Loading -> {

                            }
                            is ResultState.Success -> {
                                Timber.d(it.data.toString())
                                doOnSuccess()
                            }
                        }
                    }.collect()
                }
            }
        }
    }

}