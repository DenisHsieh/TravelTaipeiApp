package com.example.traveltaipeiapp.repository

import com.example.traveltaipeiapp.api.ApiService
import com.example.traveltaipeiapp.api.model.Attractions
import com.example.traveltaipeiapp.api.model.News
import com.example.traveltaipeiapp.util.LanguageUtil
import retrofit2.Call
import retrofit2.Response

class MainRepository {

    suspend fun getNewsData(apiService: ApiService): Response<News> {
        val call: Call<News> = apiService.getNews(LanguageUtil.LANGUAGE_ZH_TW, "2023-01-01", "2023-12-31", 1)
        val response = call.execute()
        return response
    }

    suspend fun getAttractionsData(apiService: ApiService): Response<Attractions> {
        val call: Call<Attractions> = apiService.getAttractions(LanguageUtil.LANGUAGE_ZH_TW, 1)
        val response = call.execute()
        return response
    }
}