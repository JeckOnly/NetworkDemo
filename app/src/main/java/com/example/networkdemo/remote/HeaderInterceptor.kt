package com.example.networkdemo.remote

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val  original = chain.request();

        val request = original.newBuilder()
            .header("SYS-PKG-CODE", "1")
            .header("SYS-VERSION-VALUE", "1")
            .header("DEVICE-SN", "1")
            .header("DEVICE-MODEL", "1")
            .method(original.method, original.body)
            .build();

        return chain.proceed(request);
    }
}