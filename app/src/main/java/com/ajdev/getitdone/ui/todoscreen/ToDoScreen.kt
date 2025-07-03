package com.ajdev.getitdone.ui.todoscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajdev.getitdone.domain.model.TaskModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ToDoScreen() {

    val viewModel: ToDoViewModel = koinViewModel()

    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onIntent(ToDoViewIntent.LoadTasks)
    }

    when (state.value) {
        is ToDoViewState.Loading -> CircularProgressIndicator()
        is ToDoViewState.Success -> ToDoScreenContent(
            taskList = (state.value as ToDoViewState.Success).tasks,
            onAddTask = { task-> viewModel.insertTask(task) },
        )
        is ToDoViewState.Error -> {}
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ToDoScreenContent(taskList: List<TaskModel>, onAddTask: (TaskModel) -> Unit) {

    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar tarea")
            }
        },
        content = { paddingValues ->
            ToDoBody(modifier = Modifier.padding(paddingValues), taskList)
        }
    )

    if (showDialog) AddTaskDialog(
        onDismiss = { showDialog = false },
        onConfirm = { taskModel -> onAddTask.invoke(taskModel) })
}

@Composable
fun ToDoBody(modifier: Modifier, taskList: List<TaskModel>) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(taskList) { task ->
            TaskItem(task)
        }
    }
}

@Composable
fun TaskItem(task: TaskModel) {
    Text(task.title)
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ToDoScreenPreview() {
    val list = listOf<TaskModel>(
        TaskModel(
            id = 0,
            title = "tarea 1",
            isDone = false,
            date = LocalDate.now()
        )
    )
        ToDoScreenContent(list, {})
}