package com.ajdev.getitdone.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ajdev.getitdone.data.local.converter.Converter
import com.ajdev.getitdone.data.local.dao.TaskDao
import com.ajdev.getitdone.data.local.entity.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1
)

@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}