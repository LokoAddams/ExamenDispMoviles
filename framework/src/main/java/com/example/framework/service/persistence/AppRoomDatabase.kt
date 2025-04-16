package com.example.framework.service.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookEntities::class], version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun bookhubDao(): BookDAO

    companion object {
        @Volatile
        var Instance: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, AppRoomDatabase::class.java, "book_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
