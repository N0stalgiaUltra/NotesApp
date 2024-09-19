package com.n0stalgiaultra.notesapp.presentation

import androidx.recyclerview.widget.RecyclerView
import com.n0stalgiaultra.notesapp.data.local.database.model.Note
import com.n0stalgiaultra.notesapp.databinding.NoteCardViewBinding

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