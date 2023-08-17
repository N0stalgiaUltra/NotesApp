package com.example.notesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
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
        binding.radioGroup.setOnCheckedChangeListener {
                _,
                color -> when(color){
                R.id.rbYellow -> {
                    noteColor = ContextCompat.getColor(this, R.color.note_yellow)
                    Log.i("color", "$noteColor")
                }

                R.id.rbBlue -> {
                    noteColor = ContextCompat.getColor(this, R.color.note_blue)
                    Log.i("color", "$noteColor")

                }

                R.id.rbGreen -> {
                    noteColor = ContextCompat.getColor(this, R.color.note_green)
                    Log.i("color", "$noteColor")

                }

                R.id.rbRed -> {
                    noteColor = ContextCompat.getColor(this, R.color.note_red)
                    Log.i("color", "$noteColor")

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