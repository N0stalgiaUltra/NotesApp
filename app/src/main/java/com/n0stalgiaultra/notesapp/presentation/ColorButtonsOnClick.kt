package com.n0stalgiaultra.notesapp.presentation

import android.content.Context
import androidx.core.content.ContextCompat
import com.n0stalgiaultra.notesapp.R

interface ColorButtonsOnClick {

    fun changeColor(color: Int, context: Context) : Int{
        var noteColor : Int = 0
        when (color) {
            R.id.rbYellow -> {
                noteColor = ContextCompat.getColor(context, R.color.note_yellow)

            }

            R.id.rbBlue -> {
                noteColor = ContextCompat.getColor(context, R.color.note_blue)

            }

            R.id.rbGreen -> {
                noteColor = ContextCompat.getColor(context, R.color.note_green)

            }

            R.id.rbRed -> {
                noteColor = ContextCompat.getColor(context, R.color.note_red)

            }
        }

        return noteColor
    }
}