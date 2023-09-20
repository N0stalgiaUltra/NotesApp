package com.n0stalgiaultra.notesapp.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.n0stalgiaultra.notesapp.databinding.ActivityNewTaskBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewNoteActivity : AppCompatActivity(), ColorButtonsOnClick{

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
        binding.colorButtonsGroup.radioGroup.setOnCheckedChangeListener { _,
                                                                          color ->
            noteColor = changeColor(color, this)
        }

        this.binding.btnCreateNote.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val result = mainViewModel.addNote(
                    binding.etNoteName.text.toString(),
                    noteColor
                )
                Log.i("color", "$noteColor")


                withContext(Dispatchers.Main){
                    Toast.makeText(this@NewNoteActivity,
                        "Create Note", Toast.LENGTH_SHORT)
                    finish()
                }

            }
        }
    }
}