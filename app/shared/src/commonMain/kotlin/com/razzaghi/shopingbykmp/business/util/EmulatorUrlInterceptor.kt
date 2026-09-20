package com.razzaghi.shopingbykmp.business.util

import coil3.intercept.Interceptor
import coil3.request.ImageResult

class EmulatorUrlInterceptor : Interceptor {
    override suspend fun intercept(chain: Interceptor.Chain): ImageResult {
        val data = chain.request.data

        if (data is String && (data.contains("0.0.0.0") || data.contains("localhost") || data.contains("127.0.0.1"))) {
            val fixedUrl = data
                .replace("0.0.0.0", "10.0.2.2")
                .replace("localhost", "10.0.2.2")
                .replace("127.0.0.1", "10.0.2.2")

            val newRequest = chain.request.newBuilder().data(fixedUrl).build()

            return chain.withRequest(newRequest).proceed()
        }

        return chain.proceed()
    }
}