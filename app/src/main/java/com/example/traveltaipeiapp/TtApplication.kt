package com.example.traveltaipeiapp

import android.app.Application
import android.content.Context
import com.example.traveltaipeiapp.api.ApiService
import com.example.traveltaipeiapp.util.LanguageUtil
import com.example.traveltaipeiapp.util.RetrofitManager

class TtApplication : Application() {

    val apiService: ApiService = RetrofitManager.client.create(ApiService::class.java)

    override fun onCreate() {
        super.onCreate()
        _context = this
        LanguageUtil.initAppLanguage()

    }

    companion object {
        var _context: Application? = null
        fun getContext(): Context {
            return _context!!
        }
    }
}