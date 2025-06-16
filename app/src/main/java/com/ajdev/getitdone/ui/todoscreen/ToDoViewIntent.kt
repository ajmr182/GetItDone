package com.ajdev.getitdone.ui.todoscreen

sealed interface ToDoViewIntent {
    object  LoadTasks : ToDoViewIntent
}