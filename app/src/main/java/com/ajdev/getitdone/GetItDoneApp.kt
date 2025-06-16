package com.ajdev.getitdone

import android.app.Application
import com.ajdev.getitdone.di.dataBaseModule
import com.ajdev.getitdone.di.repositoryModule
import com.ajdev.getitdone.di.useCaseModule
import com.ajdev.getitdone.di.viewModelModule
import org.koin.core.context.startKoin

class GetItDoneApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(dataBaseModule, repositoryModule, useCaseModule, viewModelModule)
        }
    }
}