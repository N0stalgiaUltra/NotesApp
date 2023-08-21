package com.example.notesapp

import com.example.notesapp.data.local.database.NoteRepositoryImpl
import com.example.notesapp.data.local.database.dao.NoteDao
import com.example.notesapp.data.local.database.model.Note
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class RepositoryUnitTest {

    @Mock
    val mockNoteDao: NoteDao = mock(NoteDao::class.java)
    @Mock
    val repository = NoteRepositoryImpl(mockNoteDao)


    @Test
    fun testInsertNote() = runBlocking{
        val note = Note(
            text = "Teste unitario de nota",
            color = R.color.note_red
        )

        repository.insert(note)

        verify(mockNoteDao).insertNote(note)
    }

    @Test
    fun testDeleteNote() = runBlocking {
        val note = Note(
            text = "Teste unitario de remocao",
            color = R.color.note_red
        )
        repository.insert(note)

        repository.delete(note_id = note.id)
        verify(mockNoteDao).deleteNote(note.id)
    }
}