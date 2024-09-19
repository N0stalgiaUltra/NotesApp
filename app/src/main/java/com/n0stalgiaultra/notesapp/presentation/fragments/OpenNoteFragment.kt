package com.n0stalgiaultra.notesapp.presentation.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.n0stalgiaultra.notesapp.R
import com.n0stalgiaultra.notesapp.databinding.ColorButtonsBinding
import com.n0stalgiaultra.notesapp.databinding.FragmentOpenNoteBinding
import com.n0stalgiaultra.notesapp.presentation.ColorButtonsOnClick
import com.n0stalgiaultra.notesapp.presentation.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class OpenNoteFragment : Fragment(), ColorButtonsOnClick {

    private var _binding: FragmentOpenNoteBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModel()
    private var noteColor = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOpenNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val noteText = requireArguments().getString("note_text")
        noteColor = requireArguments().getInt("note_color", -1)

        binding.noteEditText.setText(noteText)
        binding.root.setBackgroundColor(noteColor)

        binding.btnColorNote.setOnClickListener {
            createAlertDialog()
        }

        binding.btnConfirmNote.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                confirmEdit()
            }
            requireActivity().finish() // Finaliza a atividade, se necessário
        }

        binding.btnDeleteNote.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                removeNote()
            }
            requireActivity().finish() // Finaliza a atividade, se necessário
        }
    }

    private suspend fun removeNote() {
        mainViewModel.removeNote(requireArguments().getInt("note_id", -1))
    }

    private suspend fun confirmEdit() {
        Log.d("note", "${binding.noteEditText.text}, color: $noteColor")
        mainViewModel.editNote(
            id = requireArguments().getInt("note_id", -1),
            text = binding.noteEditText.text.toString(),
            color = noteColor
        )
    }

    private fun createAlertDialog() {
        val dialogView = layoutInflater.inflate(R.layout.color_buttons, null)
        val radioBinding = ColorButtonsBinding.bind(dialogView)

        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Choose a new color")
            .setView(dialogView)
            .setNegativeButton("Ok") { dialog, _ -> dialog.dismiss() }
            .create()

        alertDialog.show()

        radioBinding.radioGroup.setOnCheckedChangeListener { _, color ->
            noteColor = changeColor(color, requireContext())
            Log.i("note", "new background $noteColor")
            binding.root.setBackgroundColor(noteColor)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

