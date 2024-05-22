package com.example.traveltaipeiapp.api.model

import java.time.LocalDateTime

data class NewsItem(
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var begin: LocalDateTime? = null,
    var end: LocalDateTime? = null,
    var posted: String = "",
    var modified: String = "",
    var url: String = "",
    var files: List<String> = emptyList(),
    var links: List<Link> = emptyList()
)
