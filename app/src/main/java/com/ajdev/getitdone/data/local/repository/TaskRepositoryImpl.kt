package com.ajdev.getitdone.data.local.repository

import com.ajdev.getitdone.data.local.dao.TaskDao
import com.ajdev.getitdone.data.local.mapper.toDomain
import com.ajdev.getitdone.data.local.mapper.toEntity
import com.ajdev.getitdone.domain.model.TaskModel
import com.ajdev.getitdone.domain.repository.TaskRepository

class TaskRepositoryImpl(private val dao: TaskDao): TaskRepository {

    override fun getAllTasks(): List<TaskModel> {
        return dao.getAll().toDomain()
    }

    override fun insertTask(taskModel: TaskModel) {
        dao.insertAll(taskModel.toEntity())
    }
}