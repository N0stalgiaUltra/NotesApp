package com.n0stalgiaultra.notesapp.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.n0stalgiaultra.notesapp.R
import com.n0stalgiaultra.notesapp.data.local.database.model.Note
import com.n0stalgiaultra.notesapp.databinding.FragmentMainBinding
import com.n0stalgiaultra.notesapp.presentation.fragments.NewNoteFragment
import com.n0stalgiaultra.notesapp.presentation.fragments.OpenNoteFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(), CardOnClick {

    private val mainViewModel: MainViewModel by viewModel()
    private val cardAdapter: CardAdapter = CardAdapter(this)
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout do fragmento
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa a UI

        observeNotes()
        binding.fabAddNote.setOnClickListener {
            addButtonScreen()
        }
    }

    private val noteAddedReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            // Atualizar a UI, por exemplo, chamando getAllNotes() novamente
            CoroutineScope(Dispatchers.IO).launch {
                mainViewModel.getAllNotes()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter("NOTE_ADDED")
        requireActivity().registerReceiver(noteAddedReceiver, filter)
    }
    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            mainViewModel.getAllNotes()
        }
    }

    private fun observeNotes() {
        mainViewModel.notesList.observe(viewLifecycleOwner) { notes ->
            cardAdapter.clearNotes()
            cardAdapter.setNotes(notes)

            binding.emptyNotesText.visibility = if (cardAdapter.itemCount != 0) {
                View.INVISIBLE
            } else {
                View.VISIBLE
            }
            setupRecyclerView()

        }
    }

    private fun setupRecyclerView() {
        binding.mainRecView.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)
            adapter = cardAdapter
        }
    }

    private fun addButtonScreen() {
        val newNoteFragment = NewNoteFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, newNoteFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun editNote(note: Note) {
        val openNoteFragment = OpenNoteFragment().apply {
            arguments = Bundle().apply {
                putString("note_text", note.text)
                putInt("note_id", note.id)
                putInt("note_color", note.color)
            }
        }
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, openNoteFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onStop() {
        super.onStop()
        requireActivity().unregisterReceiver(noteAddedReceiver)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
