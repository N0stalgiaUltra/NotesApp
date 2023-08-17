package com.example.notesapp.data.local.database

import androidx.room.Query
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class DatabaseMigration(startVersion: Int, endVersion: Int):
    Migration(startVersion, endVersion) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE Note ADD COLUMN note_color INTEGER NOT NULL DEFAULT 0")
    }

}