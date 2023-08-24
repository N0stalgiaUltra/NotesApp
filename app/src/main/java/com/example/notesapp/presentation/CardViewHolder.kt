package com.example.notesapp.presentation

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.local.database.model.Note
import com.example.notesapp.databinding.NoteCardViewBinding

class CardViewHolder(
    private val noteCardViewBinding: NoteCardViewBinding,
    private val onClick: CardOnClick
): RecyclerView.ViewHolder(noteCardViewBinding.root) {

    fun bindText(note: Note){
        noteCardViewBinding.noteTextView.text = note.text
        noteCardViewBinding.noteCardView.setCardBackgroundColor(note.color)

        noteCardViewBinding.noteCardView.setOnClickListener {
                onClick.editNote(note)
        }
    }

}