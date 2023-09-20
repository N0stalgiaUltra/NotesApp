package com.n0stalgiaultra.notesapp.presentation

import com.n0stalgiaultra.notesapp.data.local.database.model.Note

interface CardOnClick {
    fun editNote(note: Note)
}