package com.n0stalgiaultra.notesapp.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.n0stalgiaultra.notesapp.data.local.database.model.Note
import com.n0stalgiaultra.notesapp.databinding.FragmentMainBinding
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
        setupRecyclerView()
        observeNotes()

        binding.fabAddNote.setOnClickListener {
            addButtonScreen()
        }
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
        }
    }

    private fun setupRecyclerView() {
        binding.mainRecView.apply {
            layoutManager = GridLayoutManager(context, 1)
            adapter = cardAdapter
        }
    }

    private fun addButtonScreen() {
        val intent = Intent(requireContext(), NewNoteActivity::class.java)
        startActivity(intent)
    }

    override fun editNote(note: Note) {
        val intent = Intent(requireContext(), OpenNoteActivity::class.java)
        Log.i("edit", "${note.text}")
        intent.putExtra("note_text", note.text)
        intent.putExtra("note_id", note.id)
        intent.putExtra("note_color", note.color)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
