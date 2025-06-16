package com.ajdev.getitdone.ui.todoscreen

import com.ajdev.getitdone.domain.model.TaskModel

sealed class ToDoViewState {
    data object Loading : ToDoViewState()
    data class Success(val tasks: List<TaskModel>) : ToDoViewState()
    data class Error(val message: String) : ToDoViewState()
}

