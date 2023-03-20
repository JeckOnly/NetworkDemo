package com.example.networkdemo.remote.state

sealed class ResultState<out T>(val loading: Boolean = false) {
    class Loading<T>(loading: Boolean): ResultState<T>(loading = loading)
    class Success<out T>(val data: T?): ResultState<T>(loading = false)
    class Failure<out T>(val throwable: Throwable): ResultState<T>(loading = false)
}
