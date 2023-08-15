package com.example.notesapp.data.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @ColumnInfo("note_text") val text: String,
)/*TODO:Adicionar possibilidade de cor*/{
    @ColumnInfo("note_id")
    @PrimaryKey(autoGenerate = true)var id: Int = 0

}
