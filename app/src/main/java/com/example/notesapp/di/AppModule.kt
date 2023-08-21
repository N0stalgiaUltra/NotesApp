package com.example.notesapp.di

import androidx.room.Room
import com.example.notesapp.data.local.database.AppDatabase
import com.example.notesapp.data.local.database.DatabaseMigration
import com.example.notesapp.data.local.database.NoteRepositoryImpl
import com.example.notesapp.domain.NoteRepository
import com.example.notesapp.presentation.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


//Database/Room Module
val databaseModule = module{
    single {
        Room.databaseBuilder(context = androidContext(),
        klass = AppDatabase::class.java,
        name = AppDatabase.DATABASE_NAME
        )
            .addMigrations(DatabaseMigration(1,2))
            .build()
    }

    single {
        get<AppDatabase>().noteDao()
    }
}

//View Model Module
val viewModelModule = module {
    viewModel {
        MainViewModel(repository = get())
    }
}

val repositoryModule = module {
    single <NoteRepository>{
        NoteRepositoryImpl(dao = get())
    }
}

