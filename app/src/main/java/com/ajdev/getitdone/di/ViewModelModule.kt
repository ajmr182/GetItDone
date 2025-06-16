package com.ajdev.getitdone.di

import com.ajdev.getitdone.ui.todoscreen.ToDoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ToDoViewModel(get()) }
}