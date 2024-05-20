package com.example.traveltaipeiapp.api.model

import com.google.gson.annotations.SerializedName

data class Attractions(
    @SerializedName("total")
    val total: Int,
    @SerializedName("data")
    val data: List<AttractionItem>
)
