package com.example.traveltaipeiapp.repository

import com.example.traveltaipeiapp.api.ApiService
import com.example.traveltaipeiapp.api.model.Attractions
import com.example.traveltaipeiapp.api.model.News
import com.example.traveltaipeiapp.util.LanguageUtil
import retrofit2.Call
import retrofit2.Response

class MainRepository {

    suspend fun getNewsData(lang: String, apiService: ApiService): Response<News> {
        val call: Call<News> = apiService.getNews(lang, "2023-01-01", "2023-12-31", 1)
        val response = call.execute()
        return response
    }

    suspend fun getAttractionsData(lang: String, apiService: ApiService): Response<Attractions> {
        val call: Call<Attractions> = apiService.getAttractions(lang, 1)
        val response = call.execute()
        return response
    }
}