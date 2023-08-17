package com.example.notesapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesapp.data.local.database.model.Note
import com.example.notesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), CardOnClick {


    private val mainViewModel: MainViewModel by viewModel()
    private val cardAdapter: CardAdapter = CardAdapter(this)
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    //Inicializar a UI
    override fun onStart() {
        super.onStart()

        mainViewModel.notesList.observe(this, ){
            notes -> run{
                cardAdapter.clearNotes()
                cardAdapter.setNotes(notes)

                if(cardAdapter.itemCount != 0)
                    binding.emptyNotesText.visibility = View.INVISIBLE

                setupRecyclerView()
            }
        }

        binding.fabAddNote.setOnClickListener {
            addButtonScreen()
        }
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            mainViewModel.getAllNotes()
        }
    }

    private fun setupRecyclerView(){
        binding.mainRecView.apply {
            layoutManager = GridLayoutManager(applicationContext, 1)
            adapter = cardAdapter
        }
    }

    private fun addButtonScreen(){
        val intent = Intent(
            applicationContext,
            NewNoteActivity::class.java)

        startActivity(intent)
    }

    override fun editNote(note: Note) {
        val intent = Intent(applicationContext, OpenNoteActivity::class.java)
        Log.i("edit", "$note.text")
        intent.putExtra("note_text", note.text)
        intent.putExtra("note_id", note.id)
        intent.putExtra("note_color", note.color)
        startActivity(intent)
    }
}