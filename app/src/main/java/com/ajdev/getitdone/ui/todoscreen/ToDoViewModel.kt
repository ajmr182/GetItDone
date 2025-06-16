package com.ajdev.getitdone.ui.todoscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajdev.getitdone.domain.model.TaskModel
import com.ajdev.getitdone.domain.usecases.GetAllTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ToDoViewModel(private val getAllTaskUseCase: GetAllTaskUseCase) : ViewModel() {

    private val _state = MutableStateFlow<ToDoViewState>(ToDoViewState.Loading)
    val state: StateFlow<ToDoViewState> = _state

    private val _tasks = MutableStateFlow<List<TaskModel>>(emptyList())
    val tasks: StateFlow<List<TaskModel>> = _tasks

    fun onIntent(intent: ToDoViewIntent) {
        when (intent) {
            ToDoViewIntent.LoadTasks -> getAllTask()
        }
    }

    fun getAllTask() = viewModelScope.launch(Dispatchers.IO) {
        val tasks = getAllTaskUseCase.invoke()
        _state.value = ToDoViewState.Success(tasks)
    }
}