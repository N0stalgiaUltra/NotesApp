package com.example.notesapp.presentation

import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.NoteCardViewBinding

class CardViewHolder(
    private val noteCardViewBinding: NoteCardViewBinding
): RecyclerView.ViewHolder(noteCardViewBinding.root) {

    fun bindText(text: String){
        /*TODO: Usar String Resource para cada card*/
        noteCardViewBinding.noteTextView.text = text
    }

}