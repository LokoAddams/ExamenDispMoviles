package com.example.framework.service.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDAO {

    @Query("SELECT * FROM book_table")
    fun getAccounts(): List<BookEntities>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: BookEntities)

//    @Query("DELETE FROM github_account")
//    suspend fun deleteAll()
//
//    @Query("SELECT * FROM github_account WHERE alias = :alias LIMIT 1")
//    suspend fun findByAlias(alias: String): GitAccount
}