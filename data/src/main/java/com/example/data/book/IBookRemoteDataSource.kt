package com.example.data.book

import com.example.data.NetworkResult
import com.example.domain.Libro
import java.awt.print.Book

interface IBookRemoteDataSource {
    suspend fun buscar(bookTitle: String): NetworkResult<List<Libro>>
}
