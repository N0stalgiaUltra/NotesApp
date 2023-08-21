package com.example.notesapp

import android.app.Application
import com.example.notesapp.di.databaseModule
import com.example.notesapp.di.repositoryModule
import com.example.notesapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(
                databaseModule,
                viewModelModule,
                repositoryModule
            )
        }
    }
}