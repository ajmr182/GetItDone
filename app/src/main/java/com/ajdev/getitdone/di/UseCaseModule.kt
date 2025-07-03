package com.ajdev.getitdone.di

import com.ajdev.getitdone.domain.usecases.GetAllTaskUseCase
import com.ajdev.getitdone.domain.usecases.InsertTaskUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetAllTaskUseCase(get()) }
    factory { InsertTaskUseCase(get()) }
}