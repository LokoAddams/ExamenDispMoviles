package com.example.usecases

import com.example.data.NetworkResult
import com.example.data.book.BookRepository
import com.example.data.persistence.ILocalDataSource
import com.example.domain.Libro

class ShowSavedBooks(
    val bookRepository: BookRepository
) {
    suspend fun save(): NetworkResult<List<Libro>> {
        return this.bookRepository.getFavorites()
    }
}