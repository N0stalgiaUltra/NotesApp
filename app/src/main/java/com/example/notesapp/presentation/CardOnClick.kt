package com.example.notesapp.presentation

import com.example.notesapp.data.local.database.model.Note

interface CardOnClick {
    fun editNote(note: Note)
}