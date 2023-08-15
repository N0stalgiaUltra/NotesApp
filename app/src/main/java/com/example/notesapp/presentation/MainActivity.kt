package com.example.notesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesapp.data.local.database.AppDatabase
import com.example.notesapp.data.local.database.dao.NoteDao
import com.example.notesapp.data.local.database.model.Note
import com.example.notesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private val mainViewModel: MainViewModel by viewModel()
    private val cardAdapter: CardAdapter = CardAdapter()
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
    }

    override fun onResume() {
        super.onResume()

        //IO para entrada e saida de dados
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
}