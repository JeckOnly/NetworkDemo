package com.example.networkdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkdemo.domain.interf.BackendRepo
import com.example.networkdemo.model.VeriKeyDto
import com.example.networkdemo.remote.state.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val backendRepo: BackendRepo
) : ViewModel() {

    private val mutableVeriKey: MutableStateFlow<VeriKeyDto?> = MutableStateFlow(null)

    fun getVeriKey(doOnSuccess: () -> Unit, doOnFailure: () -> Unit) {
        viewModelScope.launch {
            backendRepo.getSignKey().collect {
                when(it) {
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
}