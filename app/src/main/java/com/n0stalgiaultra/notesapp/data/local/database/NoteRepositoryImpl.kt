package com.n0stalgiaultra.notesapp.data.local.database

import com.n0stalgiaultra.notesapp.data.local.database.dao.NoteDao
import com.n0stalgiaultra.notesapp.data.local.database.model.Note
import com.n0stalgiaultra.notesapp.domain.NoteRepository

class NoteRepositoryImpl(private val dao: NoteDao): NoteRepository {
    override suspend fun insert(note: Note) {
        if(!note.text.isNullOrEmpty() && note.color != 0)
            dao.insertNote(note = note)
    }

    override suspend fun delete(note_id: Int) {
        dao.deleteNote(note_id = note_id)
    }

    override suspend fun edit(note_id: Int, text: String) {
        if(!text.isNullOrEmpty())
            dao.editNote(note_id = note_id, text= text)
    }

    override suspend fun edit(note_id: Int, color: Int) {
        dao.editNote(note_id = note_id, color=color)
    }

    override suspend fun getAllNotes(): List<Note> {
        return dao.getAllNotes()
    }
}