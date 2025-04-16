package com.example.framework.service.persistence

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity ( tableName = "book_table")
data class BookEntities(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "key")
    val key: String,

) {

}

