package com.example.traveltaipeiapp

import android.app.Application
import com.example.traveltaipeiapp.api.ApiService
import com.example.traveltaipeiapp.util.RetrofitManager

class TtApplication : Application() {

    val apiService: ApiService = RetrofitManager.client.create(ApiService::class.java)
}