package com.ajdev.getitdone.domain.usecases

import com.ajdev.getitdone.domain.model.TaskModel
import com.ajdev.getitdone.domain.repository.TaskRepository

class InsertTaskUseCase(
    private val repository: TaskRepository
) {
    operator fun invoke(taskModel: TaskModel) {
        return repository.insertTask(taskModel)
    }
}