package com.n0stalgiaultra.notesapp.data.local.database

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.n0stalgiaultra.notesapp.data.local.database.dao.NoteDao
import com.n0stalgiaultra.notesapp.data.local.database.model.Note

@Database(entities = [Note::class], version = 2)
abstract class AppDatabase : RoomDatabase(){
    abstract fun noteDao(): NoteDao

    companion object {
        const val DATABASE_NAME: String = "notes-app-db"
    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun createOpenHelper(config: DatabaseConfiguration): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }
}