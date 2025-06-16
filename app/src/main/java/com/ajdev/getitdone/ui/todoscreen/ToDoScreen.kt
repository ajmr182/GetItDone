package com.ajdev.getitdone.ui.todoscreen

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel

@Composable
fun ToDoScreen() {

    val viewModel: ToDoViewModel = koinViewModel()

    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onIntent(ToDoViewIntent.LoadTasks)
    }

    when(state.value) {
        is ToDoViewState.Loading -> CircularProgressIndicator()
        is ToDoViewState.Success -> ToDoScreenContent()
        is ToDoViewState.Error -> {}
    }
}

@Composable
fun ToDoScreenContent() {

}