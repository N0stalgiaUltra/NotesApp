package com.example.notesapp.presentation

import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.local.database.model.Note
import com.example.notesapp.databinding.NoteCardViewBinding

class CardViewHolder(
    private val noteCardViewBinding: NoteCardViewBinding,
    private val onClick: CardOnClick
): RecyclerView.ViewHolder(noteCardViewBinding.root) {

    fun bindText(note: Note){
        /*TODO: Usar String Resource para cada card*/
        noteCardViewBinding.noteTextView.text = note.text

        noteCardViewBinding.noteCardView.setOnClickListener {
                onClick.editNote(note)
        }
    }

}