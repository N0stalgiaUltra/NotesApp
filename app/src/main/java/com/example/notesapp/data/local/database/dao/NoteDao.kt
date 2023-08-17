package com.example.notesapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.notesapp.data.local.database.model.Note

@Dao
interface NoteDao {

    // Create
    @Insert
    suspend fun insertNote(note: Note)

    // Read
    @Query("SELECT COUNT(note_id) FROM Note")
    suspend fun getAllItems() : Long

    @Query("SELECT * FROM Note")
    suspend fun getAllNotes() : List<Note>

    // Delete
    @Query("DELETE FROM Note WHERE note_id = :note_id")
    suspend fun deleteNote(note_id: Int)

    // Update
    @Query("UPDATE Note SET note_text = :text WHERE note_id = :note_id")
    suspend fun editNote(note_id: Int, text: String)
    @Query("UPDATE Note SET note_color = :color WHERE note_id = :note_id")
    suspend fun editNote(note_id: Int, color: Int)
}