package com.example.notesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityOpenNoteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class OpenNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOpenNoteBinding
    private val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOpenNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.noteEditText.setText(
            intent.getStringExtra("note_text"))

        binding.root.setBackgroundColor(intent.getIntExtra("note_color", -1))
    }

    override fun onResume() {
        super.onResume()


        binding.btnConfirmNote.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch{
                confirmEdit()
            }
            finish()
        }

        binding.btnDeleteNote.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch{
                removeNote()
            }
            finish()
        }
    }

    private suspend fun removeNote(){
        mainViewModel.removeNote(intent.getIntExtra("note_id", -1))
    }
    private suspend fun confirmEdit(){
            mainViewModel.editNoteText(
                id = intent.getIntExtra("note_id", -1),
                text = binding.noteEditText.text.toString()
            )

    }



}