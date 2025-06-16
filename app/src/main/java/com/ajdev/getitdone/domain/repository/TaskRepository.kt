package com.ajdev.getitdone.domain.repository

import com.ajdev.getitdone.domain.model.TaskModel

interface TaskRepository {

    fun getAllTasks(): List<TaskModel>
}