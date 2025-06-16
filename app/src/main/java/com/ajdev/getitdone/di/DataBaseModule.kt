package com.ajdev.getitdone.di

import android.app.Application
import androidx.room.Room
import com.ajdev.getitdone.data.local.AppDatabase
import org.koin.dsl.module

val dataBaseModule = module {

    single {
        Room.databaseBuilder(
            get<Application>(),
            AppDatabase::class.java,
            "todo-db"
        )
            .build()
    }

    single { get<AppDatabase>().taskDao() }
}