package com.example.traveltaipeiapp.api.model

import java.io.Serializable

data class AttractionItem (
    val id: Int,
    val name: String,
    val name_zh: String,
    val open_status: Int,
    var introduction: String,
    val open_time: String,
    val zipcode: String,
    val distric: String,
    val address: String,
    val tel: String,
    val fax: String,
    val email: String,
    val months: String,
    val nlat: Double,
    val elong: Double,
    val official_site: String,
    val facebook: String,
    val ticket: String,
    val remind: String,
    val staytime: String,
    val modified: String,
    val url: String,
    val category: List<Category>,
    val target: List<Target>,
    val service: List<Service>,
    val friendly: List<Friendly>,
    val images: List<Image>,
    val links: List<Link>
) : Serializable
