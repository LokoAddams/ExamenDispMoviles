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
        println("here")
        this.localDataSource.save(bookTitle)
        return true
    }

    suspend fun getFavorites(): NetworkResult<List<Libro>> {
        val keysLibros =  this.localDataSource.getFavorites()
        val retorno = mutableListOf<Libro>()
        if (keysLibros is NetworkResult.Success) {
            for (book in keysLibros.data) {
                val response = remoteDataSource.buscarPorId(book.key)
                if (response is NetworkResult.Success) {
                    retorno.add(response.data)
                }
            }
        }
        return NetworkResult.Success(retorno)
    }
}