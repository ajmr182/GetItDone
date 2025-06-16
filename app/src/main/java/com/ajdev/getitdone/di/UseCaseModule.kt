package com.ajdev.getitdone.di

import com.ajdev.getitdone.domain.usecases.GetAllTaskUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetAllTaskUseCase(get()) }
}