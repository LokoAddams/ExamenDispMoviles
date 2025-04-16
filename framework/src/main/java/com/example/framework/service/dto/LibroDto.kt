package com.example.framework.service.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class LibroDto(

    @Json(name = "first_publish_year")
    val anioPublicacion: String?,
    @Json(name = "title")
    val titulo: String,
    @Json(name = "author_name")
    val autores: List<String>?,
    @Json(name = "key")
    val key: String
) {
}