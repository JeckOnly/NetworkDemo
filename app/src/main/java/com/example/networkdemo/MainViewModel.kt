package com.example.networkdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkdemo.domain.interf.BackendRepo
import com.example.networkdemo.model.ConfigCodeBody
import com.example.networkdemo.model.EXAMPLE_SIGN_KEY_BODY
import com.example.networkdemo.model.VeriKeyDto
import com.example.networkdemo.remote.state.ResultState
import com.example.networkdemo.util.EncryptUtil
import com.example.networkdemo.util.toJson
import com.example.networkdemo.util.toMd5Str
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.sign

@HiltViewModel
class MainViewModel @Inject constructor(
    private val backendRepo: BackendRepo
) : ViewModel() {

    private val mutableVeriKey: MutableStateFlow<VeriKeyDto?> = MutableStateFlow(null)

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
                ).collect {
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
                }
            }

        }
    }

}