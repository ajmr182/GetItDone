package com.ajdev.getitdone.di

import com.ajdev.getitdone.data.local.repository.TaskRepositoryImpl
import com.ajdev.getitdone.domain.repository.TaskRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<TaskRepository> { TaskRepositoryImpl(get()) }
}