package com.example.notesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityNewTaskBinding
import com.example.notesapp.databinding.ActivityOpenNoteBinding
import com.example.notesapp.databinding.ColorButtonsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class OpenNoteActivity : AppCompatActivity(), ColorButtonsOnClick{

    private lateinit var binding: ActivityOpenNoteBinding
    private val mainViewModel: MainViewModel by viewModel()
    private var noteColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOpenNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        Log.i("lifecycle", "OnStart OpenNoteActivity")
        binding.noteEditText.setText(
            intent.getStringExtra("note_text"))

        noteColor = intent.getIntExtra("note_color", -1)
        binding.root.setBackgroundColor(noteColor)
        binding.btnColorNote.setOnClickListener {
            createAlertDialog()
        }
    }

    override fun onResume() {
        super.onResume()

        Log.i("lifecycle", "OnResume OpenNoteActivity")

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
            Log.i("note", "${binding.noteEditText.text}, color: $noteColor")

            mainViewModel.editNote(
                id = intent.getIntExtra("note_id", -1),
                text = binding.noteEditText.text.toString(),
                color = noteColor
            )

    }

    private fun createAlertDialog(){
        val dialogView = layoutInflater.inflate(R.layout.color_buttons, null)
        val radioBinding = ColorButtonsBinding.bind(dialogView)

        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Choose a new color")
            .setView(dialogView)
            .setNegativeButton("Ok"){
                dialog, _ -> dialog.dismiss()
            }
            .create()

        alertDialog.show()

        radioBinding.radioGroup.setOnCheckedChangeListener { radioGroup, color ->
            noteColor = changeColor(color, this)
            Log.i("note", "new background $noteColor")
            binding.root.setBackgroundColor(noteColor)
        }

    }

}


