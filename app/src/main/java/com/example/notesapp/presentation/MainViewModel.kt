package com.example.notesapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.data.local.database.dao.NoteDao
import com.example.notesapp.data.local.database.model.Note

class MainViewModel(private val dao: NoteDao): ViewModel() {

    private val _notesList = MutableLiveData<List<Note>>()
    val notesList: LiveData<List<Note>> get() = _notesList


    suspend fun getAllNotes(){
        val notes = dao.getAllNotes()
        _notesList.postValue(notes)
    }

    suspend fun addNote(text: String, color: Int){
        dao.insertNote(Note(text, color))
    }

    suspend fun removeNote(id: Int){
        dao.deleteNote(note_id = id)
    }


    suspend fun editNote(id: Int, text: String, color: Int){
        dao.editNote(
            note_id = id,
            text = text)
        dao.editNote(
            note_id = id,
            color = color
        )
    }

}