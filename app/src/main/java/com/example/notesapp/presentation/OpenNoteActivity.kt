package com.example.notesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityOpenNoteBinding
import com.example.notesapp.databinding.NoteCardViewBinding
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
    }

    override fun onResume() {
        super.onResume()

        binding.btnConfirmNote.setOnClickListener {
            confirmEdit()
            finish()
        }

        binding.btnDeleteNote.setOnClickListener {
            deleteNote()
            finish()
        }
        //aqui entram as chamadas para os metodos da view model
    }

    private fun deleteNote(){}
    private fun confirmEdit(){}



}