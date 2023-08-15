package com.example.notesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notesapp.data.local.database.AppDatabase
import com.example.notesapp.data.local.database.dao.NoteDao
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: AppDatabase
    private lateinit var noteDao: NoteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}