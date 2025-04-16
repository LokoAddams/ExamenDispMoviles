package com.example.usecases

import com.example.data.NetworkResult
import com.example.data.book.BookRepository
import com.example.domain.Libro

class SearchBooksUseCase(
    val bookRepository: BookRepository
) {
    suspend fun invoke(bookTitle: String) : NetworkResult<List<Libro>> {
        println("estoy aca")
        return  bookRepository.findbyTitle(bookTitle)
    }
}