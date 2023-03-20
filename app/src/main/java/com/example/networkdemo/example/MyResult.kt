package com.example.networkdemo.example

sealed class MyResult<out T>(val loading: Boolean = false) {
    class Loading<T>(loading: Boolean): MyResult<T>(loading = loading)
    class Success<out T>(val data: T?): MyResult<T>(loading = false)
    class Failure<out T>(val throwable: Throwable): MyResult<T>(loading = false)
}
