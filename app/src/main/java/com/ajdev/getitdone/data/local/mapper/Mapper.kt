package com.ajdev.getitdone.data.local.mapper

import com.ajdev.getitdone.data.local.entity.TaskEntity
import com.ajdev.getitdone.domain.model.TaskModel

fun List<TaskEntity>.toDomain(): List<TaskModel> = map {
    TaskModel(
        id = it.id,
        title = it.title,
        isDone = it.isDone,
        date = it.date
    )
}