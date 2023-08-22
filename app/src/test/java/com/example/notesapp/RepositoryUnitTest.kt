package com.example.notesapp

import com.example.notesapp.data.local.database.NoteRepositoryImpl
import com.example.notesapp.data.local.database.dao.NoteDao
import com.example.notesapp.data.local.database.model.Note
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doThrow
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.Mockito.verifyZeroInteractions

class RepositoryUnitTest {

    @Mock
    val mockNoteDao: NoteDao = mock(NoteDao::class.java)
    @Mock
    val repository = NoteRepositoryImpl(mockNoteDao)


    @Test
    fun `Insert note`() = runBlocking{
        val note = Note(
            text = "Teste unitario de nota",
            color = R.color.note_red
        )

        repository.insert(note)

        verify(mockNoteDao).insertNote(note)
    }

    @Test
    fun `Cannot add empty note`() = runBlocking{
        val note = Note(
            text = "",
            color = R.color.note_red
        )

        // Call the function that adds the note
        repository.insert(note)

        // Verify that the mockNoteDao.insertNote was not called
        verifyNoInteractions(mockNoteDao)
    }


    @Test
    fun `Delete note`() = runBlocking {
        val note = Note(
            text = "Teste unitario de remocao",
            color = R.color.note_red
        )
        repository.insert(note)

        repository.delete(note_id = note.id)
        verify(mockNoteDao).deleteNote(note.id)
    }

    @Test
    fun `Cannot edit note with empty text`() = runBlocking{
        val note = Note(
            text = "text to be edited",
            color = R.color.note_red
        )

        repository.edit(note_id = note.id,
            text = "")
        verifyNoInteractions(mockNoteDao)
    }
}