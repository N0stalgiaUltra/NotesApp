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
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewTaskBinding
    private val mainViewModel: MainViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        this.binding.btnCreateNote.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val result = mainViewModel.addNote(
                    binding.etNoteName.text.toString()
                )

                withContext(Dispatchers.Main){
                    Toast.makeText(this@NewTaskActivity,
                        "Create Note", Toast.LENGTH_SHORT)
                    finish()
                }

            }
        }
    }

}