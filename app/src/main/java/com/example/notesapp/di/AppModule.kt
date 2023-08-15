package com.example.notesapp.di

import androidx.room.Room
import com.example.notesapp.data.local.database.AppDatabase
import com.example.notesapp.presentation.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


//Database/Room Module
val databaseModule = module{
    single {
        Room.databaseBuilder(context = androidContext(),
        klass = AppDatabase::class.java,
        name = "${AppDatabase.DATABASE_NAME}").build()
    }

    single {
        get<AppDatabase>().taskDao()
    }
}

//View Model Module
val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}

val repositoryModule = module { /*TODO: Fazer o modulo do repo*/ }

