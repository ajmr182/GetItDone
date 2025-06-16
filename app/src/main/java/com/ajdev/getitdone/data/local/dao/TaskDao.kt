package com.ajdev.getitdone.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ajdev.getitdone.data.local.entity.TaskEntity

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAll(): List<TaskEntity>

    @Insert
    fun insertAll(vararg users: TaskEntity)

    @Delete
    fun delete(task: TaskEntity)
}