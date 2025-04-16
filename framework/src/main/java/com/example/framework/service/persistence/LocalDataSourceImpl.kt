package com.example.framework.service.persistence

import android.content.Context
import com.example.data.NetworkResult
import com.example.data.persistence.ILocalDataSource
import com.example.domain.Libro
import com.example.framework.service.mappers.toBookEntities
import com.example.framework.service.mappers.toDto
import com.example.framework.service.mappers.toModel
import com.example.framework.service.remoteDataSource.BookRemoteDataSource

class LocalDataSourceImpl(val context: Context) : ILocalDataSource {
    val bookDAO: BookDAO = AppRoomDatabase.getDatabase(context).bookhubDao()
    override suspend fun save(book: Libro): Boolean {
        bookDAO.insert(book.toBookEntities())
        return true
    }

    override suspend fun getFavorites(): NetworkResult<List<Libro>> {
                val books = bookDAO.getFavorites()
//                val retorno = mutableListOf<Libro>()
//                for (book in books) {
//                     val response = remoteDataSource.buscarPorId(book.key)
//                    if (response is com.example.data.NetworkResult.Success) {
//                        retorno.add(response.data)
//                    }
//
//                }
                return NetworkResult.Success(books.map { it.toModel() })
            }

}