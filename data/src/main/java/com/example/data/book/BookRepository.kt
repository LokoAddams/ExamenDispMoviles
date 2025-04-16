package com.example.data.book

import com.example.data.NetworkResult
import com.example.data.persistence.ILocalDataSource
import com.example.domain.Libro
import java.awt.print.Book

class BookRepository(
    private val remoteDataSource: IBookRemoteDataSource,
    private val  localDataSource: ILocalDataSource
){

    suspend fun findbyTitle(bookTitle: String): NetworkResult<List<Libro>> {
        return this.remoteDataSource.buscar(bookTitle)
    }

    suspend fun save(bookTitle: Libro): Boolean {
        this.localDataSource.save(bookTitle)
        return true
    }
}