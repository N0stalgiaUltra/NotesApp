package com.n0stalgiaultra.notesapp.presentation.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.n0stalgiaultra.notesapp.presentation.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class NoteService : Service() {
    private val mainViewModel: MainViewModel by inject()
    private val serviceScope = CoroutineScope(Dispatchers.IO)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                "ADD_NOTE" -> {
                    val noteText = it.getStringExtra("note_text") ?: ""
                    val noteColor = it.getIntExtra("note_color", -1)
                    addNoteToDatabase(noteText, noteColor)
                }
                "EDIT_NOTE" -> {
                    val noteId = it.getIntExtra("note_id", -1)
                    val noteText = it.getStringExtra("note_text") ?: ""
                    val noteColor = it.getIntExtra("note_color", -1)
                    editNoteInDatabase(noteId, noteText, noteColor)
                }
                "REMOVE_NOTE" -> {
                    val noteId = it.getIntExtra("note_id", -1)
                    removeNoteFromDatabase(noteId)
                }
            }
        }
        return START_NOT_STICKY
    }

    private fun addNoteToDatabase(noteText: String, noteColor: Int) {
        serviceScope.launch {
            mainViewModel.addNote(noteText, noteColor)
            sendBroadcast(Intent("NOTE_ADDED"))
        }
    }

    private fun editNoteInDatabase(noteId: Int, noteText: String, noteColor: Int) {
        serviceScope.launch {
            mainViewModel.editNote(noteId, noteText, noteColor)
            sendBroadcast(Intent("NOTE_EDITED"))
        }
    }

    private fun removeNoteFromDatabase(noteId: Int) {
        serviceScope.launch {
            mainViewModel.removeNote(noteId)
            sendBroadcast(Intent("NOTE_REMOVED"))
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}