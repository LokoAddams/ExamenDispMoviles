package com.example.data.persistence

import com.example.data.NetworkResult
import com.example.domain.Libro
import java.awt.print.Book

interface ILocalDataSource {
    suspend fun save(book: Libro): Boolean
    suspend fun getFavorites(): NetworkResult<List<Libro>>
}
