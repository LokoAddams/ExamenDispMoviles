package com.example.framework.service.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ListaLibrosDto(
    @Json(name = "docs")
    val libros: List<LibroDto>
)
{
}