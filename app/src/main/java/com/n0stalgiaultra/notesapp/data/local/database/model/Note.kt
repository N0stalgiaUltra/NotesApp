package com.n0stalgiaultra.notesapp.data.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @ColumnInfo("note_text") val text: String,
    @ColumnInfo("note_color") val color: Int
){
    @ColumnInfo("note_id")
    @PrimaryKey(autoGenerate = true)var id: Int = 0

}
