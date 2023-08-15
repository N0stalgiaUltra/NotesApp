package com.example.notesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.notesapp.R
import com.example.notesapp.data.local.database.AppDatabase
import com.example.notesapp.data.local.database.dao.NoteDao
import com.example.notesapp.data.local.database.model.Note
import com.example.notesapp.databinding.ActivityNewTaskBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewTaskBinding
    private lateinit var database: AppDatabase
    private lateinit var noteDao: NoteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.database = AppDatabase.getInstance(this)
        this.noteDao = this.database.taskDao()
    }

    override fun onStart() {
        super.onStart()

        this.binding.btnCreateNote.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val result = saveNote(
                    binding.etNoteName.text.toString()
                )

                withContext(Dispatchers.Main){
                    Toast.makeText(this@NewTaskActivity,
                        "Create Note", Toast.LENGTH_SHORT)
                }
            }
        }
    }

    private suspend fun saveNote(noteText: String){
        noteDao.insertTask(Note(noteText))
        Log.i("db", "note created $noteText")
    }
}