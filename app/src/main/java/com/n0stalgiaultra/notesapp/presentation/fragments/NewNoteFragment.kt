package com.n0stalgiaultra.notesapp.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.n0stalgiaultra.notesapp.R
import com.n0stalgiaultra.notesapp.databinding.FragmentNewNoteBinding
import com.n0stalgiaultra.notesapp.presentation.ColorButtonsOnClick
import com.n0stalgiaultra.notesapp.presentation.MainViewModel
import com.n0stalgiaultra.notesapp.presentation.broadcastreciever.NoteReceiver
import com.n0stalgiaultra.notesapp.presentation.service.NoteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewNoteFragment : Fragment(), ColorButtonsOnClick {

    private var _binding: FragmentNewNoteBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var noteColor = 0
        binding.colorButtonsGroup.radioGroup.setOnCheckedChangeListener { _, color ->
            noteColor = changeColor(color, requireContext())
        }

        binding.btnCreateNote.setOnClickListener {
            createNote(noteColor)
        }
    }

    private fun createNote(noteColor: Int) {
        val intent = Intent(requireContext(), NoteReceiver::class.java).apply {
            action = "ADD_NOTE"
            putExtra("note_text", binding.etNoteName.text.toString())
            putExtra("note_color", noteColor)
        }
        requireContext().sendBroadcast(intent)

        // Navegue de volta ao MainFragment após criar a nota
        requireActivity().supportFragmentManager.popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
