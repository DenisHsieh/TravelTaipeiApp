package com.example.traveltaipeiapp.api

import com.example.traveltaipeiapp.api.model.Attractions
import com.example.traveltaipeiapp.api.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("{lang}/Events/News")
    fun getNews(
        @Path("lang") lang: String,
        @Query("begin") begin: String,
        @Query("end") end: String,
        @Query("page") page: Int
    ): Call<News>

    @GET("{lang}/Attractions/All")
    fun getAttractions(
        @Path("lang") lang: String,
        @Query("page") page: Int
    ): Call<Attractions>
}