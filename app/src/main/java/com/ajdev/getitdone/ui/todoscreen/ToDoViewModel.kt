package com.ajdev.getitdone.ui.todoscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajdev.getitdone.domain.model.TaskModel
import com.ajdev.getitdone.domain.usecases.GetAllTaskUseCase
import com.ajdev.getitdone.domain.usecases.InsertTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ToDoViewModel(
    private val getAllTaskUseCase: GetAllTaskUseCase,
    private val insertTaskUseCase: InsertTaskUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<ToDoViewState>(ToDoViewState.Loading)
    val state: StateFlow<ToDoViewState> = _state

    private val _tasks = MutableStateFlow<List<TaskModel>>(emptyList())
    val tasks: StateFlow<List<TaskModel>> = _tasks

    private val _taskToAdd = MutableStateFlow<TaskModel?>(null)
    val taskToAdd: StateFlow<TaskModel?> = _taskToAdd

    fun onIntent(intent: ToDoViewIntent) {
        when (intent) {
            ToDoViewIntent.LoadTasks -> getAllTask()
        }
    }

    fun getAllTask() = viewModelScope.launch(Dispatchers.IO) {
        val tasks = getAllTaskUseCase.invoke()
        _state.value = ToDoViewState.Success(tasks)
    }

    fun insertTask(task: TaskModel) = viewModelScope.launch(Dispatchers.IO) {
        insertTaskUseCase.invoke(task)
    }

    fun updateTaskToAdd(taskModel: TaskModel) = viewModelScope.launch {
        _taskToAdd.value = taskModel
    }
}