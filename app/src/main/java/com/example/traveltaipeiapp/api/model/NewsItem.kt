package com.example.traveltaipeiapp.api.model

import java.time.LocalDateTime

data class NewsItem(
    val id: Int,
    var title: String,
    var description: String,
    val begin: LocalDateTime?,
    val end: LocalDateTime?,
    val posted: String,
    val modified: String,
    val url: String,
    val files: List<String>,
    val links: List<Link>
)
