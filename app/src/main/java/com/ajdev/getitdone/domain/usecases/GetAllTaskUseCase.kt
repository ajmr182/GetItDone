package com.ajdev.getitdone.domain.usecases

import com.ajdev.getitdone.domain.model.TaskModel
import com.ajdev.getitdone.domain.repository.TaskRepository

class GetAllTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(): List<TaskModel> {
        return repository.getAllTasks()
    }
}