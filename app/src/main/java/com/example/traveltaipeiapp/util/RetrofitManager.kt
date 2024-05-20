package com.example.traveltaipeiapp.util

import com.example.traveltaipeiapp.BuildConfig
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitManager private constructor() {
    private val retrofit: Retrofit
    private val okHttpClient = createOkHttpClient()

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .addNetworkInterceptor(LoggerInterceptor())
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
            .build()
    }


    companion object {
        private val manager = RetrofitManager()
        val client: Retrofit
            get() = manager.retrofit
    }
}