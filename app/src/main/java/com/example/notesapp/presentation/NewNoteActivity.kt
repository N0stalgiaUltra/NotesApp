package com.example.notesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityNewTaskBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewTaskBinding
    private val mainViewModel: MainViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        var noteColor = 0
        this.binding.radioGroup.setOnCheckedChangeListener {
                _,
                color -> when(color){
                R.id.rbYellow -> {
                    noteColor = R.color.note_yellow
                }

                R.id.rbBlue -> {
                    noteColor = R.color.note_blue
                }

                R.id.rbGreen -> {
                    noteColor = R.color.note_green
                }

                R.id.rbRed -> {
                    noteColor = R.color.note_red
                }
            }
        }

        this.binding.btnCreateNote.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val result = mainViewModel.addNote(
                    binding.etNoteName.text.toString(),
                    noteColor
                )

                withContext(Dispatchers.Main){
                    Toast.makeText(this@NewNoteActivity,
                        "Create Note", Toast.LENGTH_SHORT)
                    finish()
                }

            }
        }
    }

}