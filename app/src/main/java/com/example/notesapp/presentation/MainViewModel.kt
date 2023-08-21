package com.example.notesapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.data.local.database.NoteRepositoryImpl
import com.example.notesapp.data.local.database.dao.NoteDao
import com.example.notesapp.data.local.database.model.Note
import com.example.notesapp.domain.NoteRepository

class MainViewModel(private val repository: NoteRepository): ViewModel() {

    private val _notesList = MutableLiveData<List<Note>>()
    val notesList: LiveData<List<Note>> get() = _notesList


    suspend fun getAllNotes(){
        val notes = repository.getAllNotes()
        _notesList.postValue(notes)
    }

    suspend fun addNote(text: String, color: Int){
        repository.insert(Note(text, color))
    }

    suspend fun removeNote(id: Int){
        repository.delete(note_id = id)
    }


    suspend fun editNote(id: Int, text: String, color: Int){
        repository.edit(
            note_id = id,
            text = text)
        repository.edit(
            note_id = id,
            color = color
        )
    }

}