package com.example.domain

import kotlinx.serialization.Serializable

@Serializable
data class Libro(
    val titulo: String = "",
    val autores: List<String>? = null,
    val anioPublicacion: String? = null,
    val key : String
)
