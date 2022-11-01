package com.kitech.noteit.ui.Notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.kitech.noteit.R;
import com.kitech.noteit.databinding.FragmentNotesBinding;
import com.kitech.noteit.domain.NoteEntity;
import com.kitech.noteit.ui.Notes.viewmodels.NotesViewModel;

import java.util.List;

public class NotesFragment extends Fragment  {

    private FragmentNotesBinding binding;
    private NotesViewModel mViewModel;
    private NoteRecyclerViewAdapter notesAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentNotesBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getNotes();

        binding.addNewNoteButton.setOnClickListener(view1 -> NavHostFragment.findNavController(NotesFragment.this)
                .navigate(R.id.action_NotesFragment_to_createNoteFragment));

    }


    private void getNotes() {
        mViewModel.getAllNotes().observe(getViewLifecycleOwner(), notes ->{
            if (!notes.isEmpty())
                loadNotes(notes);
            else
                Toast.makeText(getContext(), "no notes", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadNotes(List<NoteEntity> notes) {
        notesAdapter = new NoteRecyclerViewAdapter(noteId ->
        loadNoteDetails(noteId)
        );
        binding.notesRecyclerView.setAdapter(notesAdapter);
        notesAdapter.setNotes(notes);
    }

    private void loadNoteDetails(long noteId) {
        Bundle bundle = new Bundle();
        bundle.putLong("NOTE_ID", noteId);
        NavHostFragment.findNavController(NotesFragment.this)
                .navigate(R.id.action_NotesFragment_to_createNoteFragment, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}