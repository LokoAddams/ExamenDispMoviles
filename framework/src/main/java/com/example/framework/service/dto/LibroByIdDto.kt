package com.example.framework.service.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class LibroByIdDto(
    @Json(name = "title")
    val title: String,
    @Json(name = "subject_people")
    val authors: List<String>?,
    @Json(name = "key")
    val key: String,
)
{
}