package com.ajdev.getitdone.ui.todoscreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

@Composable
fun ToDoScreen() {

    val viewModel: ToDoViewModel = koinViewModel()

    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onIntent(ToDoViewIntent.LoadTasks)
    }

    when (state.value) {
        is ToDoViewState.Loading -> CircularProgressIndicator()
        is ToDoViewState.Success -> ToDoScreenContent()
        is ToDoViewState.Error -> {}
    }
}

@Preview
@Composable
fun ToDoScreenContent() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO */ }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar tarea")
            }
        },
        content = { paddingValues ->
           ToDoBody(modifier = Modifier.padding(paddingValues))
        }
    )
}

@Composable
fun ToDoBody(modifier: Modifier) {

}