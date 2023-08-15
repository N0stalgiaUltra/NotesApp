package com.example.notesapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.notesapp.data.local.database.model.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insertTask(note: Note)

    @Query("SELECT COUNT(note_id) FROM Note")
    suspend fun getAllItems() : Long
}