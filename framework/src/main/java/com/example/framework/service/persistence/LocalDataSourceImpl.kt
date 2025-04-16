package com.example.framework.service.persistence

import android.content.Context
import com.example.data.persistence.ILocalDataSource
import com.example.domain.Libro
import com.example.framework.service.mappers.toBookEntities
import com.example.framework.service.mappers.toDto

class LocalDataSourceImpl(val context: Context) : ILocalDataSource {
    val bookDAO: BookDAO = AppRoomDatabase.getDatabase(context).bookhubDao()
    override suspend fun save(book: Libro): Boolean {
        bookDAO.insert(book.toBookEntities())
        return true
    }

    override suspend fun findByUser(title: String): Libro {
        TODO("Not yet implemented")
    }
}