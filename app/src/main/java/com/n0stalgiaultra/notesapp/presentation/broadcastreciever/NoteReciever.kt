package com.n0stalgiaultra.notesapp.presentation.broadcastreciever
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.n0stalgiaultra.notesapp.data.local.database.model.Note
import com.n0stalgiaultra.notesapp.presentation.MainViewModel
import com.n0stalgiaultra.notesapp.presentation.service.NoteService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class NoteReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            val serviceIntent = Intent(context, NoteService::class.java).apply {
                action = it.action
                putExtra("note_text", it.getStringExtra("note_text"))
                putExtra("note_color", it.getIntExtra("note_color", -1))
                putExtra("note_id", it.getIntExtra("note_id", -1))
            }
            context?.startService(serviceIntent)
        }
    }
}
