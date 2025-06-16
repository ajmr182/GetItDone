package com.ajdev.getitdone.domain.model

import java.time.LocalDate

data class TaskModel(
    val id: Int = 0,
    val title: String,
    val isDone: Boolean = false,
    val date: LocalDate
)