package com.example.notesapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.local.database.model.Note
import com.example.notesapp.databinding.NoteCardViewBinding

class CardAdapter(private val cardOnClick: CardOnClick): RecyclerView.Adapter<CardViewHolder>() {

    private var _notes: List<Note>? = emptyList()

    fun setNotes(notes: List<Note>?){
        this._notes = notes
    }
    fun clearNotes(){
        _notes = emptyList()
    }

    //Cria o VH
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = NoteCardViewBinding.inflate(from, parent, false)

        return CardViewHolder(noteCardViewBinding = binding, onClick = cardOnClick)
    }

    override fun getItemCount(): Int {
        return _notes?.size ?: 0
    }

    //Seta o texto
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val note = _notes?.get(position)
        if(note != null)
            holder.bindText(note)
    }
}