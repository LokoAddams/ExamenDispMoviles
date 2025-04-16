package com.example.framework.service.mappers

import com.example.domain.Libro
import com.example.framework.service.dto.LibroDto
import com.example.framework.service.persistence.BookEntities

fun LibroDto.toModel(): Libro {
    return Libro(
        titulo = titulo,
        autores = autores,
        anioPublicacion = anioPublicacion,
        key = key
    )
}

fun Libro.toDto(): LibroDto {
    return LibroDto(
        titulo = titulo,
        autores = autores,
        anioPublicacion = anioPublicacion,
        key = key
    )
}

fun Libro.toBookEntities(): BookEntities {
    return BookEntities(
        key = key
    )
}

fun BookEntities.toModel(): Libro {
    return Libro(
        key = key
    )
}