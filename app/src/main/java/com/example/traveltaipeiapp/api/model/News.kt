package com.example.traveltaipeiapp.api.model

import com.google.gson.annotations.SerializedName

data class News (
    @SerializedName("total")
    val total: Int,
    @SerializedName("data")
    val data: List<NewsItem>
)