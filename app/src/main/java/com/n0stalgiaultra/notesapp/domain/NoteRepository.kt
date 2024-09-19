package com.n0stalgiaultra.notesapp.domain

import com.n0stalgiaultra.notesapp.data.local.database.model.Note

interface NoteRepository {
    suspend fun insert(note: Note)
    suspend fun delete(note_id: Int)
    suspend fun edit(note_id: Int, text: String)
    suspend fun edit(note_id: Int, color: Int)
    suspend fun getAllNotes(): List<Note>
}